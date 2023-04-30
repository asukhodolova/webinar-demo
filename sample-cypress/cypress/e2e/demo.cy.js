describe('Cypress Google search', () => {

    const url = 'https://www.google.com/';
    const searchValue = 'Zebrunner';

    it('Should return first result that contains search value', () => {
        cy.visit(url).contains('Google');

        console.log(`Performing Google search`);
        cy.xpath("//*[@name='q']").click().type(searchValue).type('{enter}');

        console.log(`Verify first search result contains search value`);
        cy.xpath("//*[@id='search']//a").should('contain.text', searchValue);
    });

    it('Should return first result that equals search value', () => {
        cy.visit(url).contains('Google');

        console.log(`Performing Google search`);
        cy.xpath("//*[@name='q']").click().type(searchValue).type('{enter}');

        console.log(`Verify first search result equals search value`);
        cy.xpath("//*[@id='search']//a").should('have.text', searchValue);
    });

});
