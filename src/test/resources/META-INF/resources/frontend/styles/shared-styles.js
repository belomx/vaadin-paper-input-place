// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `

<dom-module id="input-place" theme-for="paper-input-place">
  <template>
    <style>
    </style>
  </template>
</dom-module>
`;

document.head.appendChild($_documentContainer.content);
