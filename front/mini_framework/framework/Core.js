/**
 * Create vDom Node
 * @param tagName
 * @param options
 * @returns {{children: ([{children: *, classNames: *, attributes: *, tagName: *}]|[string]|[{children: *, classNames: *, attributes: *, tagName: *}]|[string]|HTMLCollection), classNames: [string], attributes: ActiveX.ISchemaItemCollection | boolean | NamedNodeMap | ActiveX.IXMLDOMNamedNodeMap, tagName: *}}
 */
export function createElement(tagName, options) {
  return {
    tagName: tagName,
    classNames: options.classNames,
    attributes: options.attributes,
    children: options.children
  }
}

/**
 * Transform a vDOM Node to real DOM Node
 * @param vNode
 * @returns {Node}
 */
export function renderVDOM(vNode) {
  // Create TextNode if vNode is a string
  if (typeof vNode === 'string') {
    return document.createTextNode(vNode);
  }

  // Create DOM Element
  const domNode = document.createElement(vNode.tagName);

  // Add class
  vNode.classNames && domNode.classList.add(...vNode.classNames);

  // Add attribute
  vNode.attributes && vNode.attributes.forEach(({ name, value }) => domNode.setAttribute(name, value));

  // Render recursively children
  vNode.children && vNode.children.forEach(child => domNode.appendChild(renderVDOM(child)));

  return domNode;
}

/**
 * Mount DOM to the target
 * @param DOM
 * @param target
 */
export function mountDOM(DOM, target) {
  target.replaceChildren(DOM);
  return DOM;
}

/**
 * Create patch (diff) between two vDom
 * @param oldVNode
 * @param newVNode
 */
export function makePatch(oldVNode, newVNode) {

  // Remove DOM node if we have nothing in our new node
  if (newVNode === undefined) {
    return domNode => domNode.remove();
  }

  // Check if we have a string node
  if (typeof oldVNode === 'string' || typeof newVNode === 'string') {
    if (oldVNode !== newVNode) {
      return domNode => domNode.replaceWith(renderVDOM(newVNode));
    }
    return () => { };
  }

  // Replace DOM node if their tag are different
  if (oldVNode.tagName !== newVNode.tagName) {
    return domNode => domNode.replaceWith(renderVDOM(newVNode));
  }

  // Prepare a minimal tab containing old children and new children
  // E.g: If we have an old array like [0,2,3] and a new one like [0,2,4,6]
  // We want to patch [0,2,3] with the new elements [0,2,4]
  // The [6] element of the new array will be added after diffing [0,2,3] and [0,2,4]
  const childPatches = [];
  const childrenPair = [];
  for (let i = 0; i < Math.min(oldVNode.children.length, newVNode.children.length); i++) {
    childrenPair.push([oldVNode.children[i], newVNode.children[i]]);
  }

  // E.g [3] of the old array and [4] of the new one
  childrenPair && childrenPair.forEach(([oldVNodePair, newVNodePair]) => {
    childPatches.push(makePatch(oldVNodePair, newVNodePair))
  });

  // Add new children
  // E.g: In the last example we want to add [6] element of the new array
  const addChildPatches = [];
  newVNode.children.slice(oldVNode.children.length).forEach((newChildNode) => {
    addChildPatches.push(domNode => domNode.appendChild(renderVDOM(newChildNode)));
  });

  // Apply patches
  return domNode => {
    for (let i = 0; i < childPatches.length; i++) {
      childPatches[i](domNode.childNodes[i]);
    }
    addChildPatches && addChildPatches.forEach((addChildPatch) => addChildPatch(domNode));
  };
}
