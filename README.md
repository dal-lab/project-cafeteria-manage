# cafeteria-manage
달랩 10주 프로젝트 (구내식당 식단 관리 및 재료 산정 서비스)

## 1주차
TODO (우선순위 정렬)
- [X] GET /hello 응답 기능 구현하기
- [X] GET /hello 요청 기능 구현하기
- [X] 프론트엔드 첫화면에 “hello cafeteria mgmt” 나오게 하기
- [X] MenuPlan 도메인 구현하기 (id, workDayId, menuId)
- [X] Menu 도메인 구현하기 (id, name)
- [X] WorkDay 도메인 구현하기 (id, day, date)
- [X] 새로운 식단 입력을 위해 POST /workDay/{2019-09-30}/menuPlans endpoint 만들기
    [
		{
			"name": "제육볶음"
		},
		...
    ]
- [X] MenuPlanRequestDto 만들기
- [X] 요청받은 MenuPlan 저장 기능 만들기 (service, repository)
- [X] GET /workDay/{date} Member 응답 기능 만들기
    [
        {	
            "day": "월",
            "date": "20190930"
            "menus": [
                {
                    "name":
                },
                ...
            ]
        }
    ]
- [X] GET /workDay Collection 응답 기능 만들기
    [
        {	
            "day": "월",
            "date": "20190930"
            "menus": [
                {
                    "name":
                },
                ...
            ]
        }
    ]
- [X] 기존 식단을 보여주기 위한 제목 나오게 하기
- [X] 기존 식단 보여주기 제목 밑에 5 * 12로 된 표? 리스트? 출력하기 -> 5*2표로 두번째 줄에 그냥 내용 다 때려넣음
- [X] 기존 식단 내용을 보여주기 위해 GET /workDay 요청 기능 만들기
- [X] GET /workDay 통해 받은 응답 출력하기
- [ ] 메뉴가 없는 날 메뉴 출력칸에 '메뉴 입력' 버튼 만들기 => webpack에 if 거는 법 모르겠음 그냥 무조건 나오게 변경
- [ ] ModelMapper 가능한 걷어내보기(굳이 필요한가에 대해서 고민할 것)
- [ ] 엔티티에 연관관계 매핑 추가
- [ ] Menu CR 기능 구현
- [ ] WorkDay CR 기능 구현
- [ ] 요청받은 MenuPlan 저장 기능 중 Menu가 없는 경우 예외 처리하기
- [ ] 요청받은 MenuPlan 저장 기능 중 WorkDay가 없는 경우 예외 처리하기

