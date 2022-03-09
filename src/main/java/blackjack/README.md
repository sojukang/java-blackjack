# 블랙잭 🃏

### 블랙잭 용어 사전

- 한장 더 : HIT
- 그만 받음 : STAY
- 21이 넘어서 탈락 : BUST
- 카드를 나눠줌 : DRAW
- 카드 명칭 🃏
    - 숫자인 카드 : pip
    - JQK : face
    - Ace : ace
    - heart (❤️)
    - club (🍀)
    - spade (♠️)
    - diamond (💎)

## 기능 요구 사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다. 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며,
두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의
카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 기능 구현 목록

- [x] 처음 시작하면 모든 참가자가 카드를 2장씩 받는다.
- 딜러
    - [x] 딜러는 처음 받은 카드 한 장을 공개한다.
    - [x] 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받음
    - [x] 17점 이상이면 추가로 받을 수 없음
- 플레이어
    - [ ] BUST 하지 않는다면 카드를 계속 받을 수 있다.
- 카드
    - [x] 카드는 중복을 허용하지 않는다.
    - [x] 하트, 클럽, 스페이드, 다이아몬드
    - [x] JQK (10을 계산)
    - [x] ACE (1 또는 11로 계산)
    - [x] 카드가 ACE인지 확인한다.
    - [x] 2 ~ 10
    - [x] 여러 장의 카드를 관리한다.
    - [x] 모든 카드의 값을 합산한다.
    - [x] 카드를 관리하는 객체에 카드를 추가한다.
- [x] 합이 21 또는 21에 가장 가까운 숫자
- [x] 합이 21 초과면 BUST
- [x] 카드 뽑기
- [ ] 승패 출력
- 게임 진행
    - [ ] 각 플레이어가 STAY이거나 BUST이면 다음 플레이어가 카드를 받는다.
    - [ ] 모든 플레이어의 카드 받기가 끝나면 딜러가 받을 차례가된다.
    - [ ] 모든 참가자가 BUST이면 무승부(PUSH)가 된다.
    - [ ] 딜러가 BUST이면 BUST가 아닌 다른 플레이어들은 가진 패와 상관없이 승리한다.

> 플레이어 이름 입력

- [x] **쉼표(,)** 를 기준으로 입력받는다.
- [x] 이름의 길이는 **최대 100자**까지만 허용된다.

> HIT 여부 입력

- [x] **y** 혹은 **n** 만 허용된다.
- [x] 대소문자는 구분하지 않는다.

> Model

- 딜러
- 플레이어
- 카드

> 메시지

- [ ] 게임을 시작한다
- [x] 플레이어들의 이름을 입력 받는다.
- [x] 플레이어들을 생성한다.
- [x] 모든 참가자들에게 카드를 2장씩 분배한다.
- [ ] 각 플레이어가 `HIT | STAY` 를 결정한다.
- [x] HIT 라면 카드르 한장 더 생성한다.
- [x] 생성한 카드를 플레이어에게 할당한다.
- [ ] STAY 라면 다음 참가자에게 순서가 넘어간다.
- [ ] 모든 플레이어 카드를 다 뽑았는지 확인한다.
- [ ] 모든 플레이어가 카드를 다 뽑으면 딜러가 카드를 뽑는다.
- [x] 만약 딜러가 16초과면 카드를 뽑지않는다.
- [x] 각 참가자의 카드 값을 합산한다. 
- [ ] 결과를 계산한다.
- [ ] 결과를 출력한다.
  