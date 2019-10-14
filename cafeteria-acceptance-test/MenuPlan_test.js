
Feature('MenuPlanTest');

Scenario('주간_메뉴_리스트_확인_테스트', (I) => {
    I.amOnPage('http://localhost:3000');
    I.see('주간 식단');
  });
