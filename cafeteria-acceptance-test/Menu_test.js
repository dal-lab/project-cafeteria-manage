
Feature('MenuTest');

Scenario('메뉴 관리 페이지 이동 확인', (I) => {
    I.amOnPage('http://localhost:3000/menus');
    I.see('메뉴 관리');
});

Scenario('메뉴_입력_페이지_확인', (I) => {
    I.amOnPage('http://localhost:3000/menus');
    I.click({ css: 'button.addMenu' });
    I.fillField('메뉴명', '제육볶음');
    
});