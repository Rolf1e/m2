import { createElement, renderVDOM, mountDOM, makePatch } from "./framework/Core.js";
import { div, p, br } from "./framework/Components.js";

const firstVDOM = div(
  ['main-content'],
  [p([], ['Lorem ipsum'])]
);

const secondVDOM = div(
  ['main-content'],
  [p([],
    [
      'Lorem ipsum 2',
      br(),
      createElement('strong', { classNames: ['bold'], children: ['Strong text'] })
    ]
  )]
);

// First DOM state
let DOM = renderVDOM(firstVDOM);
mountDOM(DOM, document.getElementById('app'));

// Apply diff between firstVDOM and secondVDOM
setTimeout(() => {
  const patch = makePatch(firstVDOM, secondVDOM);
  DOM = patch(DOM);
}, 2000)
