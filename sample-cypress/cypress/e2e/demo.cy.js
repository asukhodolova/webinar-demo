describe('Google search', () => {

    const url = 'https://www.google.com/';

    it('Should return first result that contains search value', () => {
        const searchValue = 'Zebrunner';
        cy.visit(url).contains('Google');

        console.log(`Performing Google search`);
        cy.xpath("//*[@name='q']").click().type(searchValue).type('{enter}');

        console.log(`Verify first search result contains search value`);
        cy.xpath("//*[@id='search']//a").should('contain.text', searchValue);
    });

    it('Should return first result that equals to search value', () => {
        const searchValue = 'Cypress';
        cy.visit(url).contains('Google');

        console.log(`Performing Google search`);
        cy.xpath("//*[@name='q']").click().type(searchValue).type('{enter}');

        console.log(`Verify first search result equals to search value`);
        cy.xpath("//*[@id='search']//a").should('have.text', searchValue);
    });

});
