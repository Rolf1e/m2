import { createElement } from "./Core.js";

export function div(classNames, children) {
  return createElement('div', { children: children, classNames: classNames });
}

export function p(classNames, children) {
  return createElement('p', { children: children, classNames: classNames });
}

export function br() {
  return createElement('br', {});
}
