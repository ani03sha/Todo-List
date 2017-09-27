import { TodoappAngularPage } from './app.po';

describe('todoapp-angular App', () => {
  let page: TodoappAngularPage;

  beforeEach(() => {
    page = new TodoappAngularPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
