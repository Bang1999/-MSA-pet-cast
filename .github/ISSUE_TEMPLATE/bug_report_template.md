---
name: BUG_REPORT_TEMPLATE
about: 버그 사항 한 줄 요약
title: "[Domain-XXX]"
labels: bug
assignees: ''

---

# 버그 제목
- 기능 명세 번호: FEAT-XXX
- 링크와 스크린샷을 첨부하시면 설명력이 좋아집니다 :)

>
> 모두가 이해할 수 있게 설명해주세요.
> 불필요한 설명을 제외하고 필요한 부분에 대해서 일목요연하게 설명해주세요.
>

## 문제 상황 설명
1. 특정 데이터를 쌓습니다.
2. 해당 데이터를 조회합니다
3. 다른 테이블로 해당 테이블 조인 시 문제가 발생합니다.

### 문제 설명
- 문제 상황에 대한 설명을 해주세요

### 에러 로그 공유
- StackOverFlow
```
java.lang.StackOverflowError: null
at com.ecomhunt.entities.Order.hashCode(Order.java:15) ~[classes/:na]
at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:124) ~[na:na]
at com.ecomhunt.entities.Customer.hashCode(Customer.java:11) ~[classes/:na]
at com.ecomhunt.entities.Order.hashCode(Order.java:15) ~[classes/:na]
at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:124) ~[na:na]
at com.ecomhunt.entities.Customer.hashCode(Customer.java:11) ~[classes/:na]
at com.ecomhunt.entities.Order.hashCode(Order.java:15) ~[classes/:na]
at java.base/java.util.AbstractSet.hashCode(AbstractSet.java:124) ~[na:na]
at com.ecomhunt.entities.Customer.hashCode(Customer.java:11) ~[classes/:na]
```

### 실행 환경 설명
- OS: [e.g. iOS]
- Framework: [Mybatis]
- Browser [e.g. chrome, safari]
- Version [e.g. 22]

### 추가 설명
- 필요시 추가