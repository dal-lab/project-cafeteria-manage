
Feature('MainpageTest');

Scenario('첫 화면 잘 뜨는지 확인', (I) => {
    I.amOnPage('http://localhost:3333');
    I.see('베니스 F&S');
    I.see('식단 관리');
    I.see('메뉴 관리');
    I.see('식자재 관리');
});