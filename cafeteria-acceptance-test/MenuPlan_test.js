
Feature('MenuTest');

Scenario('식단관리 관리 페이지 이동 확인', (I) => {
    I.amOnPage('http://localhost:3333/menuPlans');
    I.see('식단 관리');
});

Scenario('식단 입력 테스트(10월)', (I) => {
    I.amOnPage('http://localhost:3333/menuPlans');
    I.see('새 식단 추가')
    I.click('새 식단 추가');
    I.fillField('workMonth', '10');
    I.click('식단 추가');
    I.see('10월');
});

// Scenario('메뉴 삭제 테스트(제육볶음)', (I) => {
//     I.amOnPage('http://localhost:3333/menus');
//     I.click('제육볶음');
//     I.click('메뉴 삭제')
//     I.dontSee('제육볶음')
// });

// Scenario('메뉴 상세 테스트(제육볶음)', (I) => {
//     I.amOnPage('http://localhost:3333/menus');
//     I.click('제육볶음');
//     I.see('메뉴 상세');
//     I.fillField('menuName', '오징어볶음');
//     I.fillField('메뉴 수정');
//     I.see('오징어볶음')
// });