
Feature('MenuTest');

Scenario('메뉴 관리 페이지 이동 확인', (I) => {
    I.amOnPage('http://localhost:3333/menus');
    I.see('메뉴 관리');
});

// 이대로 두면 쓰래기 데이터가 너무 쌓여서 테스트가 어려울 것 같음 -> 삭제도 추가
Scenario('메뉴 입력 테스트(제육볶음)', (I) => {
    I.amOnPage('http://localhost:3333/menus');
    I.see('새 메뉴 추가')
    I.click('새 메뉴 추가');
    I.fillField('menuName', '제육볶음');
    I.click('메뉴 추가');
    I.see('제육볶음');
});