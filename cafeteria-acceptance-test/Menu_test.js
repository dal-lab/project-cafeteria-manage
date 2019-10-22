
Feature('MenuTest');

Scenario('메뉴 관리 페이지 이동 확인', (I) => {
    I.amOnPage('http://localhost:3333/menus');
    I.see('메뉴 관리');
});

Scenario('메뉴_입력_페이지_확인', (I) => {
    I.amOnPage('http://localhost:3333/menus');
    I.click({ css: 'a.new-menu-btn' });
    I.see('새 메뉴 추가')
});