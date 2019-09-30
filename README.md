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
- [ ] 새로운 식단 입력을 위해 POST /workDay/{20190930}/menuPlans endpoint 만들기
    [
		{
			"name": "제육볶음"
		},
		...
    ]
- [X] MenuPlanRequestDto 만들기
- [ ] 요청받은 데이터 저장 기능 만들기 (service, repository)
- [ ] GET /workDay/{20190930} 응답 기능 만들기
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
- [ ] 기존 식단을 보여주기 위한 제목 나오게 하기
- [ ] 기존 식단 보여주기 제목 밑에 5 * 12로 된 표? 리스트? 출력하기
- [ ] 기존 식단 내용을 보여주기 위해 GET /workDay/{20190930} 요청 기능 만들기
- [ ] GET /workDay/{20190930} 통해 받은 응답 출력하기
- [ ] 새로운 식단 입력을 위해 식단 입력 제목 나오게 하기
- [ ] 새로운 식단 입력을 위해 요일 입력칸 만들기
- [ ] 새로운 식단 입력을 위해 메뉴 입력칸 12개 만들기
- [ ] 입력 된 식단 저장을 위한 저장 버튼 만들기
