# G.G_Project 

- JAVA기반 탄막게임을 모티브로 둔 미니게임 만들기
  
  

## 게임 플레이 주요 메서드
```
- Game() 생성자: 게임 화면을 초기화하고 필요한 객체들을 생성. 게임의 초기 설정, 플레이어 및 몬스터의 초기 위치, 체력 등을 설정 try-catch문 이용 이미지파일을 불러오는 과정에서 예외가 발생할 경우 e.printStackTrace()를 호출해 스텍 추적 내용을 출력, 어느부분에서 발생한 예외인지 파악후 수정

- actionPerformed(ActionEvent e): 주요 게임 로직이 포함된 메서드/ 게임의 상태 변화, 이동, 충돌 감지 등을 처리 / 주요 동작으로는 플레이어 및 몬스터의 이동, 공격 등이 있음.아이템과의 충돌, 몬스터의 체력 및 스테이지 진행 상황을 확인 /화면을 다시 그리기 위해 repaint()를 호출

- paintComponent(Graphics g): 게임 화면을 그리기 위한 메서드. 배경, 플레이어, 몬스터, 발사체 등을 그리고 플레이어 및 몬스터의 체력, 아이템 상태 등을 화면에 표시

-checkItemCollision(): 플레이어와 아이템의 충돌 감지후 처리 

- keyPressed(KeyEvent e) 및 keyReleased(KeyEvent e): 키보드 이벤트를 처리하는 메서드. 사용자가 키를 누를 때와 뗄 때의 동작을 처리하고 이동 및 발사 동작에 대한 상태를 업데이트

- firePlayerProjectile(): 플레이어가 발사체를 발사하는 메서드입. 현재 플레이어의 위치 및 방향을 기반으로 발사체를 생성하고, playerProjectiles 리스트에 추가

- resetGame(): 게임을 초기 상태로 재설정하는 메서드. 플레이어와 몬스터의 위치를 초기화하고, 체력, 발사체 등을 초기화
★clear() 메서드로 발사체 리스트를 항상 비워주는것이 좋은 프로그래밍 관행

attack1(), attack2(ActionEvent e), attack3(ActionEvent e): 몬스터의 공격 패턴을 처리하는 메서드. 각각 다른 공격 패턴에 따라 몬스터가 발사체를 생성하고, 이를 monsterProjectiles 리스트에 추가/스테이지 진행에 따라 공격 패턴이 달라짐!

위의 메서드들은 게임의 로직을 담당하고 플레이와 목스터의 동작, 공격, 이동 등을 처리하게끔 진행했습니다. 또, 키보드 이벤트를 감지해 발사체를 만들고 게임상태 초기화 등을 담당하게 했습니다.


```

## 메인 화면 주요 메서드
```
- EndingCredit() 생성자: 객체 초기화 및 설정 메서드(initObject() 및 initSetting())를 호출하고, 창을 표시하기 위해 setVisible(true)을 호출

- initObject() 메서드: UI 요소와 이벤트 핸들러를 초기화

backgroundMain: 배경화면 이미지를 JLabel로 설정
backIcon 및 backhovericon: 뒤로가기 버튼에 사용할 이미지 아이콘을 로드
backButton: 뒤로가기 버튼을 생성하고 위치와 스타일을 설정 그리고 이벤트 리스너를 등록
initSetting() 메서드: 프레임의 제목, 크기, 레이아웃, 위치 및 종료 동작을 설정

코드의 주요 목적은 EndingCredit 클래스를 사용하여 엔딩 크레딧 화면을 표시하고, 뒤로가기 버튼을 클릭하면 MainFrame 클래스의 인스턴스를 만들어 표시했습니다.
```





