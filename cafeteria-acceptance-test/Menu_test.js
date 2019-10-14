
Feature('MenuTest');

Scenario('메뉴_입력_페이지_확인', (I) => {
    I.amOnPage('http://localhost:3000/menus');
    I.see('메뉴 관리');
  });

Scenario('메뉴_입력_페이지_확인', (I) => {
    I.amOnPage('http://localhost:3000/menus');
    I.fillField('메뉴명', '제육볶음')
    I.click({ css: 'button.submit' })
  });