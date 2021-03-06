## 데스크톱 애플리케이션
1. PC에 설치되어 실행되 웹 애플리케이션 보다 실행속도가 빠름.
    - office, 컴퓨터 게임 등등...
2. 문제점
  - 배포의 번거로움.
  - 보안에 취약 : 데이터 베이스에 접근할 때 취약 가능성
  - 애플리케이션 실행할 때 먼저 서버에 갱신된 버전이 있는지 조회 후 실행하면 어느정도 해결됨.
  - 단 보안에 취약...
  
## 클라이언트'서버 애플리케이션
  - 애플리케이션의 기능을 서버와 클라이언트로 분리.
  - 장점
    - 새로 프로그램이 수정 되도 서버 쪽만 변경하면 됨. 
    - 기능 변경/추가에 유연하게 대처가 됨. 
  - 문제점 개선 방안
    - 한번에 하나의 클라이언트 하고만 연결 됨. 
    - 멀티 프로세스와 멀티 스레드로 위 문제를 해결한다.
  - 멀티 프로세스와 멀티 스레드
    - 멀티 프로세스는 원본 프로세스의 메모리를 모두 복제해 자원 낭비가 심함.
    - 멀티 쓰레드는 전체 메모리를 복제할 필요가 없음. 
    
## 다중 클라이언트 요청 처리
  - 클라이언트의 요청 처리 부분을 별도의 작업으로 분리
  - 분리된 작업은 스레드에 정의
  - 다중 클라리언트 요청이 병행 처리됨.
  - C/S 환경의 프로그래밍이 복잡해지는 문제가 생김.
  
## 클라이언트'서버 아키텍처의 진화
1. 전통적인 클라이언트'서버 아키텍처
    - 서버는 데이터 처리를 맡고 클라이언트는 UI와 비즈니스를 처리했다.
    - 단점
        - 프로그램이 변경되면 PC에 다시 설치해야됨...
        - 클라이언트가 DBMS로 바로 접속해 보안 문제 발생.
        
2. 개선된 클라이언트'서버 아키텍처
    - 클라이언트의 업무 처리부분은 서버로 이관.
    - 클라이언트는 오직 UI만 담당.
    - 서버로부터 결과를 받으면 클라이언트에서 화면을 꾸며 뿌려줌. 
    - 애플리케이션 서버?
        - 업무를 전담하게된 서버를 애플리케이션 서버라 부른다. 
        - 클라이언트로부터 요청을 받으면 업무 로직에 따라 DBMS 서버를 사용해 데이터를 처리.
        - 클라이언트 접근제어도 함꼐 처리.
    - 서버에 기능이 변경 되도 바로 클라이언트에 적용할 수 있다. 

## 웹 애플리케이션 아키텍처
1. 특징
    - 클라이언트와 통신은 웹 서버가 전담
    - 애플리케이션 서버는 애플리케이션 서버 실행 및 관리에 집중.
    - 비즈니스 로직과 UI 로직을 모두 서버에 배치되 추가 변경되도 서버만 바꾸믄 된다. 
    - 단 웹 환경에선 애플리케이션 실행할 때마다 UI로직을 내려받기 때문에 오버헤드 발생.
    - 멀티 스레드 부분을 웹 브라우저와 웹 서버가 알아서 해줌. 
    
2. 서버쪽 
    - HttpServlet 클래스르르 상속받고 있다. 
    - doGet() / doPost()를 재정의 한다. 
    
3. 화면관련 오버헤드는 어떻게 극복할까?
    - AJAX
        - 같은 화면에서 데이터만 바뀔 때 서버에 UI 전체를 받아오기 보다, 데이터만 받아온다. 
 
 ## GET 요청
   - 결과 화면을 보관하거나 결과 화면을 다른 사람과 쉽게 공유하고 플때 사용한다. 
   - 이미지나 동영상 같은 바이너리 파일은 보낼 수 없다. 
   
## POST 요청
    - 요청 결과를 공유할 수 없다. 
    
## 파일 업로드
    - form 태그의 enctype을 multipart/form-data로 지정.
    - 메세지 보낼 때 '&', '='를 보내면 구분하기 어려워 boundary값을 이용한다.
    - 이 구분자는 웹 브라우저에서 임의로 생성함. 
    - 파트 구분자 다음 라인에 매개 변수가 온다. 
    - 매개변수 데이터가 파일이면 첨부파일 이름과 콘텐츠 유형 정보가 온다. 
    - 공백라인 이후 부터 다음 파트 구분자를 만날 떄 까지 첨부 파일 데이터가 온다. 
    
    
# 서블릿 프로그래밍
## CGI 프로그램과 서블릿
  - CGI 이해
    - 웹 애플리케이션 실행은 웹 브라우저가 웹 서버에게 실행을 요청한다.
    - 서버는 클라이언트가 요청한 프로그램을 찾아 실행하고, 해당 프로그그램은 작업을 수행 후 결과를 웹 서버에 전달한다. 
    - 그담 웹 서버는 HTTP 형식에 맞춰 브라우저에게 보낸다. 
    - 여기서 웹 서버와 프로그램 사잉에 데이터를 주고 받는 규칙을 CGI라 한다. 
    - 여기서 프로그램을 CGI 프로그램이라 한다. 
    - CGI 프로그램은 실행속도는 빠르지만, 변경이 있을시 매번 컴파일 하고 재 배포해야 한다. 
    - 이와 다르게 스크립트라는 방식은 실행할 때마다 소스 코드의 문법을 검증하고 해석해 실행 속도가 느리다. 
    - 하지만 변경사항 있을 시 단지 코드 수정만하면 되기 땜에 편리하다. 
  
  - 서블릿
    - 자바로 만든 CGI 프로그램을 서블릿 이라한다.
    - 서블릿 컨테이너 
        - 서블릿 생명주기를 관리. 서블릿을 대신해 CGI 규칙에 따라 웹 서버와 데이터 주고 받는다. 
    - 웹 서버 <---> 서블릿 컨테이너 <--> 서블릿
    
## 서블릿,JSP / Java EE / WAS
  - Java EE 기술은 기업용 어플리케이션과 클라우드 애플리케이션 개발에 필요한 여러가지 복합 기술을 정의하고 모아놓은 것.
  - WAS의 이해 
    - 클라이언트/서버 구조에서 서버쪽 애플리케이션의 생성과 실행, 소멸을 관리하는 프로그램을 애플리케이션서버라 한다.
    - 그 중 서블릿과 서블릿 컨테이너 같이 웹 기술을 기반으로 동작 하는 것을 애플리케이션서버를 WAS라 부른다.
    - Java에서 WAS란, Java EE 기술 사양을 준수해 만든 서버를 말한다. 
    - JEUS, WebLogin, WebSphere, JBoss 등이 있다. 
    - 서블릿 컨테이너 : Java EE 기술 중 서블릿, JSP 등 웹 과련 부분만 구현한 서버. 
    - 이런 서버를 '웹 컨테이너', '서블릿 컨테이너'라고도 하고 톰켓, 제티 등이 있다. 
    
## 서블릿 만들기
  - 서블릿 클래스는 반드시 javax.servlet.Servlet 인터페이스를 구현해야 한다. 
  - 서블릿 생명주기를 관리하는 메서드
    - init() : 초기화에 주로 이용.
    - service() : 클라이언트가 요청할 때 마다 호출되는 메서드. 서블릿이 해야할 일을 작성하믄 된다. 
    - destroy() : 서블릿 컨테이너가 종료, 웹 애플리케이션이 멈출 때, 또는 해당 서블릿이 비활성화 시킬 때 호출.  마무리작업을 작성하믄됨.
  - 기타 메서드
    - getServletConfig() : 서블릿 설정정보를 다루는 ServletConfig 객체 반환.
    - getServletInfo() : 서블릿 정보가 담김. 
    - 서블릿을 만들었으면 배치파일(web.xml) 약어로 'DD파일'에 등록해야 한다. 
    - DD 파일에 등록되지 않는 서블릿은 서블릿 컨테이너가 찾을 수 없다. 
    - '<servlet-name>'은 서블릿 별명을 설정. 
    - '<servlet-class>'는 패키지 이름을 포함한 서블릿 클래스명을 설정. 
    - 클라이언트에서 서블릿 실행을 요청할 때 URL을 사용. URL을 부여해야 클라이언트에서 요청할 수 있다. 
    - '<servlet-mapping>'태그로 서블릿과 URL을 매핑한다. 
    - 서블릿 구동 절차
        - 1) 클라이언트 요청이 오면 서블릿 컨테이너는 서블릿을 찾는다.
        - 2) 만약 서블릿이 없다면, 서블릿 클래스를 로딩하고 인스턴스 준비 후 생성자 호출한다.
        -    그리고 서블릿 초기화 메서드 init()을 호출한다. 
        - 3) 클라이언트 요청을 처리하는 service() 메서드 호출한다. 
        - 4) service() 메서드에서 만든 결과를 HTTP 프로토콜에 맞춰 클라이언트에 응답해서 요청처리를 완료한다. 
        - 5) 만약 서블릿 컨테이너를 종료하거나 웹 애플리케이션 종료하면 서블릿 컨테이너는 종료 전 destroy() 함수를 호출한다. 
     - 서블릿 컨테이너는 클라이언트로부터 요청이 오면 해당 서블릿을 찾아보고 없으면 해당 서블릿 인스턴스를 생성한다.
     - 한번 서블릿 객체가 생성되면 웹 애플리케이션을 종료할 때 까지 계속 유지한다. 
     - 웰컴 파일 
        - 웹 서버에 요청시 서블릿 이름을 생략하고, 디렉터리 위치까지만 입력하면 웹 서버는 해당 디렉터리의 웰컴 파일을 보내준다. 
        - web.xml에 <welcom-file-list> 안에 정의 되어 있다. 
        
## 웹 애플리케이션 배치
  - 웹 애플리케이션을 서블릿 컨테이너에 배치  
  - 운영 서버에 배치
    - 실 운영 서버에 배치할 땐 배치할 파일들을 하나의 웹 아카이브 파일(.war)로 만들어 배치 폴더에 복사한다. 
    
## GenericServlet 이용
  - GenericServlet은 추상메서드인데 하위 클래스에게 공통의 필드와 메서드를 상속해 주고자 존재. 
  - 서블릿 클래스가 필요로한 init(), destroy(), getServletConfig(), getServletInfo()를 미리 구현해 상속해 줌, 
  - service()는 각 서블릿 마다 구현해야 하므로, 구현 되지 않았음. 
  - 이로써 사용자는 service() 메서드만 구현해 주믄 되게 되었다.      
  - servie() 매개변수
    - ServletRequest : 클라이언트의 요청 정보를 다룰 때 사용. 
        - getParameter()는 GET이나 POST 요청이 들어온 매개변수 값을 꺼낼 때 사용. 
        > 
            localhost:8080/web01/show?a=20&b=10 요청을 했을 때 
            request.getParameter("a"); 하면 ? 뒤의 a 값이 들어가게 된다.
    - servletResponse : 응답과 관련된 기능 제공. 클라이언트에게 보낼때 사용. 
        - getWriter() : 클라이언트로 출력할 수 있도록 출력 스트림 객체를 반환. 바이너리 출력하고플땐 getOutputStream()이용.
        - getWriter() 호출 전에 setContenType(), setCharacterEncoding()을 먼저 호출해야 한다. 
  - @WebServlet 애노테이션을 이용한 서블릿 배치 정보 설정
    - Servlet3.0 이후 부터 애노테이션으로 서블릿을 배치할 수 있다. 
    - 작성한 서블릿 클래스 이름 위에 @WebServlet을 붙이믄 된다. 
    - 서블릿 URL 정보는 @WebServlet의 '()' 안에 넣는다.
        >   @WebServlet("show")
         

# 서블릿과 JDBC
## HttpServlet으로 GET요청 다뤄보기
  - HttpServlet 클래스를 사용하면 service() 메서드 대신, doGet(), doPost()를 정의해야 한다. 
  - 클라이언트 요청이 들어오면, 첫째로 상속받은 HttpServlet의 service() 메서드가 호출된다.
  - 그 다음 service()는 클라이언트 요청 방식에 따라 doGet(), doPost(), doPut()...등 메서드를 호출한다. 
  - 그러므로 service()메서드 직접 구현보단 doXXX()메서드를 오버라이딩 하자. 
  - GET 요청이니까 doGet()을 오버라이딩 하믄 된다. 
  - cf) 배치 정보가 바뀌었다면 서블릿 컨테이너를 다시 시작해야 한다.         
  - '<form>' 태그의 action속성은 실행할 서블릿의 URL주소이다. 
  - method 속성이 'get'이면 해당 서블릿에 doGet()을 호출하고, 'post'면 doPost()를 호출하도록 한다.

## HttpServlet으로 POST요청 다뤄보기
  - doPost()를 오버라이딩 하자

## 리프래시
  - doPost() 메서드에서 다음 줄을 추가하자.
  - response.addHeader("Refresh", "1;url=list");  // '1'은 1초뒤 해당 url로 이동하라는 뜻. 상대경로로('/'가 안붙으면 상대경로)
  - 위는 HTTP 응답 정보에 헤더를 추가하는 작업이다. 
  - 작업 결과를 출력한 후 다른 페이지로 이동할땐 리프레시를 사용한다.
  - 작업 결과를 출력하지 않고 바로 다른 페이지로 이동할 경우 리다이렉트로 처리한다. 

## 리다이렉트
  - 서블릿이 응답값으로 이동할 페이지 URL 정보를 같이 보낸다.
  - 웹 브라우저가 이를 받는 즉시 해당 URL로 다시 요청한다.
  - 해당 서블릿은 해당 URL로 응답을 보낸다.
  - HttpServletRespnse의 sendRedirect()로 처리하면 된다.
  - response.sendRedirect("list");   
  - 리다이렉트는 클라이언트로 본문을 출력하지 않는다. 
  - 응답 코드는 302 이다. 이코드는 요청한 자원이 다른 URL로 이동되었으니 Location헤더에 있는 주소로 다시요청부탁해' 라는 뜻이다.

## 서블릿 초기화 매개변수
  - 서블릿을 생성하고 초기화 할때(init()호출), 서블릿 컨테이너가 전달하는 데이터이다. 
  - DD파일(web.xml)로 설정할 수도있고, 애노테이션으로 설정할 수 있다.
  - 가능한 소스코드와 분리해 외부 파일에 두는것을 추천한다. 
  - 데이터베이스 연결정보 같은 것을 둔다.
  - web.xml에서 '<servlet>'태그 안에 있는 '<init-parm>' 태그안에 작성하면 된다. 
  - 서블릿 초기화 매개변수들으느 오직 그 매개변수를 선언한 서블릿에서만 사용가능하다. 
  - 서블릿 클래스에서 this.getInitParameter("변수이름"); 으로 값을 가져온다. 

## 컨텍스트 초기화 매개변수
  - JDBC 드라이버와 연결정보같은 것을 각각의 서블릿 마다 초기화 매개변수를 선언한다면 너무 귀찮아진다.
  - 이런경우 컨텍스트 초기화 매개변수를 사용하도록 하자.
  - 같은 웹 애플리케이션에 소속된 서블릿들이 이를 공유하게 된다. 
  - web.xml에서 <context-param> 태그가 바로 이것이다. 
  - 해당 서블릿에서 사용하기 위해서 ServletContext sc = this.getServletContext(); 작성해 사용하도록 한다. 
  - sc.getInitParameter()로 초기화 매개변수값을 얻도록 한다. 

## 필터를 사용해보자
  - 필터는 서블릿 실행 전후에 어떤 작업을 할떄 사용하는 기술이다. 
  - 클라이언트 <--> 서블릿컨테이너 <--> 필터1 <--> 필터2 <--> 서블릿
  - 필터가 없었다면 서블릿안에 해당 작업을 넣어 줬어야 했다.
  - 예를 들어 POST 요청으로 메시지 바디에 한글 내용을 가져오려고 할때 setCharaterEncoding()을 해야한다.
  - 이 작업을 모든 서블릿에 적용한다면 유지보수에 좋지 안핟. 
  - 이럴때 서블릿 필터를 이용해 해결하면 된다. 
  - 필터는 javax.servlet.Filter 인터페이스를 구현하면 된다. 
  - init() : 필터 객체가 생성되고 나서 준비작업을 위해 딱 한번 호출 된다. 
  - doFilter() : 필터와 연결된 URL에 대해 요청이 들어오면 doFilter()가 항상 호출된다. 필터가 할 일을 작성하자. 
  - FilterChain nextFilter; //  다음 필터를 호출한다. 다음 필터가 없으면 내부적으로 서블릿의 service()를 호출한다. 
  - 사전작업 --> nextFileter.doFileter() --> 사후작업
  - DD파일에서 '<filter>' 안에 작성하면 된다. '<filter-mapping>'은 어떤 요청에 대해 필터를 적용할지 설정해준다. 
  - 애노테이션으로도 사용가능하다. 
 
 #MVC 아키텍처
 ## 뷰를 분리해보자
  - 서블릿에서 했던 페이지 작업을 JSP에 요청 위임하고, 서블릿에서 준비한 데이터를 JSP에 전달해본다.
  - 서블릿은 UI를 생성하지 않기 때문에 출력코드가 필요 없다. 그 대신 JSP가 화면을 만들 수 있도록 데이터를 준비해야 한다. 
  - ArrayList객체를 준비해 회원 목록을 담아 주자. ArrayList<Member> members = new ArrayList<Member>();
  - request에 회원 목록 데이터 보관한다.
	> 	request.setAttribute("members", members);
  - RequestDispatcher를 이용한 forward, include
    - 다른 서블릿이나 JSP로 작업을 위임할때 사용하는 객체가 RequestDispathcer이다. 
    - HttpServletRequest를 통해 얻는다. RequestDispatcher rd = request.getRequestDispatcher("/member/MemberListjsp");
    - Dispatcher 얻을 때, 반드시 어떤 서블릿(또는 JSP)로 위임할지 알려주야 한다. 
    - 이제 해당 jsp에서 서블릿이 넘겨준 회원 목록을 꺼내고자 request.getAttribute();를 호출한다. 
    - request는 JSP내장 객체가 선언안해서 사용가능하다. 
    - 출력은 다음 형식으로 한다. <a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>

## 포워딩과 인클루딩
  - 포워드 방식은 작업을 한번 위임하면 다시 이전 서블릿으로 제어권이 돌아가지 않는다. 
  - 인클루드 방식은 다른 서블릿으로 작업 위임 후, 그 서블릿이 끝나면 다시 이전 스블릿으로 제어권이 돌아간다. 
  - 예외가 발생하면 포워딩으로 예외 화면을 보여주자. 
  >
    catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher(
					"Error.jsp");
			rd.forward(request, response);  ;
      // 인클루디는 rd.include(request,response)
		}
  - 인클루딩은 화면 헤더와 푸터를 등록하는데 사용 된다. 
    > MemberList.jsp에 추가
      <jsp:include page="/Header.jsp"/>
      ...
      <jsp:include page="/Tail.jsp"/>

## 데이터 보관소
  - 서블릿 기술은 데이터 공유를 위해 4가지 종류의 데이터 보관소를 제공한다. 
  - 보관소는 공유 범위 기준으로 구분 된다. 
  - 1) ServletContext 보관소
    - 웹 애플리케이션이 시작될 때 생성되, 웹 애플리케이션 종료될때까지 유지. 
    - 모든 서블릿이 사용할 수 있다. 
  - 2) HttpSession 보관소
    - 클라이언트 최초 요청 시 생성되 브라우저 닫을때 까지 유지. 
    - 보통은 로그인할때 이 보관소 초기화 하고, 로그아웃하면 저장된 값들을 비운다. 
  - 3) ServletRequest 보관소
    - 클라이언트 요청이 들어올 떄 생성, 클라이언트에게 응답할 떄까지 유지됨. 
    - 포워딩, 인클루딩하는 서블릿들 사이에서 값을 공유할 때 유용. 
  4) JspContext 보관소
    - JSP 페이지를 실행하는 동안만 유지.
  
  - 보관소에 값을 넣고 쓰는 방법
    - 객체.setAttribute(키, 값); // 저장
    - 객체.getAttribute(키);
  - ServletContext의 활용
    - 데이터베이스 커넥션 객체 같은 것을 공유하고자 사용할떄 쓰면 된다. 
    - 이렇게 하면 데이터베이스를 이용하는 모든 서블릿은 ServletContext에서 DB 커넥션 객체를 가져올 수 있다. 
    - HttpServlet을 상속받은 AppInitServlet 클래스에서 init() 메서드에 작성한다.
    >  ServletContext sc = this.getServletContext();
       Class.forName(sc.getInitParameter("driver"); ...
       sc.setAttribute("conn",conn);  // 모든 서블릿이 사용할 수 있도록 ServletContext 객체에 저장한다. 
    - 서블릿 객체는 클라이언트의 최초 요청시 생성된다. 한번도 요청 없으면 그 서블릿은 생성안된다. 
    - 서블릿이 생성되기 전에 미리 작업을 준비해야 하는 서블릿의 경우 클라이언트 요청이 없더라도 웹 애플리케이션이 시작할때 자동 생성되야 한다.
    - 이를 위해 DD파일에 servlet 등록시 <load-on-startup>1</load-on-startup> 태그를 추가해주자.
    - 이런 준비 서블릿의 경우 <servlet-mapping>  태그가 없다. 
    - 이제 기존에 서블릿마다 작성해 뒀던 Connction 소스를 다음과 같이 수정하면 된다.
    >   
        ServletContext sc = this.getServletContext();
			  conn = (Connection)sc.getAttribute("conn");
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery(
				  	"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
				  	" FROM MEMBERS" +
				  	" ORDER BY MNO ASC");
			
			  response.setContentType("text/html; charset=UTF-8");
  - HttpSession 활용 - 로그인
    - 클라이언트 당 한개가 생성된다. 
    - 웹 브라우저 요청이 들어오면, 그 웹 브라우저를 위한 HttpSession 객체가 있는지 검사하고, 없으면 새로 HttpSession객체 만든다.
    - 일정기간 동안 Timeout요청이 없으면 삭제된다. 
    - /auth/login 서블릿 요청이 있을 때 LoginServlet은 회원 정보를 데이터베이스에 찾아 Member에 담고, 다른 서블릿들이 참조할 수 있도록 HttpSession객체에 보관한다. 
    >   HttpSession session = request.getSession();
        session.setAttribute("member",member);
        response.sendRedirect("../member/list");
    - <meta http-equiv="Refresh" content="1;url=login"> // 1초후 loing url로 이동하르는 뜻
    - <% Member member = (Member)session.getAttribute("member");%>로 꺼내올 수 있다. 
    - JSP내장객체 session을 써서 꺼내 올 수 있다. 
  - HttpSession 활용 - 로그아웃
    - LogOutServlet은 HttpSession 객체를 없애기 위해 invalidate()를 호출한다. 
    - 이 후 새로운 요청이 들어오면 HttpSession객체가 새로 만들어 진다. 
  - ServletRequest 활용
    - 포워딩이나 인클루딩을 통해 협업하는 서블릿끼리 데이터를 공휴할 수 있다. 
    - forword(), doGet() 메서드 모두 request를 인자로 받는데, 이것이 바로 공유할 request객체이다. 
  - JspContext의 활용
    - JSP 페이지 내부에서만 사용될 데이터 공유.
    - JSP 태그 핸들러에 데이터를 전달하고자 할떄 사용한다. 

  - DAO 만들기
    - DAO란 데이터를 처리를 전문으로 하는 객체이다. 
    - 기존 서블릿에서 하던 데이터베이스 연동 코드를 MemberDao클래스로 이관한다. 
    - MemberDao에선 ServletContext에 접근할 수 없다. 그래서 ServletContext에 보관된 DB Conncetion정보를 얻어오려고 할때, 외부로부터 Connection 객체를 주입 받기 위한 세터 메서드와 인스턴스 변수를 준비한다. 
      >   
          Connection coon;
          public void setConnection(Connection conn) {
            this.conn = conn;
          }
    - 이렇게 작업에 필요한 객체를 외부로부터 주입받는 것을 '의존성주입(DI)'라고 한다. == IoC
    - 여기까지 작업으로 서블릿은 컨트롤러, Dao는 모델, JSP는 뷰의 역할 구분이 되었다. 

## ServletContextListener와 객체 공유
  - 서블릿 컨테이너는 웹 애플리케이션의 상태를 모니터링 할 수 있도록 웹 애플리케이션 시작에서 종료까지 주요한 사건에 대해 알림 기능을 제공한다. 
  - 사건이 발생했을 때 알림을 받는 객체를 리스너 라고 부른다. 
  - 규칙에 따라 객체를 만들어 DD파일에 등록하면 된다. 
    > 웹애플리케이션 <--> 서블릿 컨테이너 --> 리스너 
  - ServletContextListener의 활용
    - 1. 웹 애플리케이션 시작하면, 서블릿 컨테이너는 ServletContextListener의 구현체에 대해 contextInitialized() 호출.
    - 2. 리스너는 DB 커넷션 객체생성 --> DAO 객체 생성 --> DB 커넷션 객체를 DAO에 주입.
    - 3. 서블릿들이 DAO 객체를 사용할 수 있도록 ServletContext 보관소에 저장. 
    - 4. 애플리케이션 종료하면 서블릿 컨테이너는 리스너의 contextDestroyed() 호출. 
  - 리스너 ServletContextListener 만들기
    - ServletContextListener 인터페이스를 구현해야 한다. 
    - contextInitalized()는 웹 애플리케이션이 시작될 때 호출 됨 .공용 객체를 준비한다면 여기에 작성.
    - contextDestoryed()는 웹 애플리케이션 종료되기 전에 호출. 자원해제는 여기에 넣음. 
  - ContextLoadListener의 배치
    - 애노테이션을 활용한 방법이 있다. class 위에 @WebListener를 붙인다. 
    - 두번쨰는 DD파일을 이용하는 것이다. <listener> 태그안에 작성한다. 

## DB 커넷션 풀
  - DB 커넥션 객체를 여러개 생성해 Pool에 담아 놓고 필요할때 쓰는 개념. 
  - DB 커넥션 객체 여러개 생성할 필요성?
    - 같은 커넥션에서 Statement가 3개 생되었다고 가정.
    - 한개에서 예외가 발생 --> 롤백을 해야함.
    - Statement는 롤백기능이 없음 --> 커넥션 객체를 통해 롤백 수행
    - 같은 커넥션 객체 공유해서 모든 작업이 롤백 됨...
    - 그러므로 각 요청에 대해 개별 DB 커넥션을 사용할 필요가 있다. 
    - SQL작업 할 때마다 DB 커넥션을 생성한다면 속도면에서 매우 좋지 않다.
    - 그래서 풀을 사용하도록 한다. 
  - DB 커넥션 풀 만들기
> 
  package spms.util;

  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.util.ArrayList;

  public class DBConnectionPool {
	  String url;
	  String username;
	  String password;
	  ArrayList<Connection> connList = new ArrayList<>();
  
  	public DBConnectionPool(String driver, String url, String username, String password) throws Exception {
	  	this.url = url;
		  this.username = username;
		  this.password = password;
		  Class.forName(driver);
	  }
	
	  public Connection getConnection() throws Exception {
		  if(connList.size()>0) {
			  Connection conn = connList.get(0);
			  if(conn.isValid(10)) {
				  return conn;
		  	}
		  }
		  return DriverManager.getConnection(url, username, password);
	  }
  
	  public void returnConnection(Connection conn) throws Exception {
		  connList.add(conn);
	  }
  
  	public void closeAll() {
		  for(Connection conn : connList) {
			  try {conn.close();} catch(Exception e) {}
		  }
  	}
  }

  - ContextLoaderSistener에서 DBConnectionPool 생성 및 DAO에 주입
  
## DataSource와 JNDI
  - javax.sql 
    - java.sql 패키지의 기능을 보조하기 위해 만든 확장 패키지아다. 
    - DriverManager를 대체하는 DataSource 인터페이스 제공.
    - Connection 및 Starement 객체의 풀링
    - 분산 트랜젝션 처리
    - Rowsets의 지원
   
  - DataSource
    - 서버에서 관리하기 때문에 데이터베이스나 JDBC 드라이버가 변경되도 애플리케이션을 바꿀 필요가 없다. 
    - DriverManager는 웹 애플리케이션에서 관리해 데이터베이스의 주소가 바뀌거나 JDBC가 변경될 경우 웹 애플리케이션의 코드도 변경해야 한다.
    - Datasource를 사용하면 Connection과 Statement 객체를 풀링할 수 있으며, 분산 트랜잭션을 다룰 수 있다. 
    - 웹 애플리케이션 쪽에서 따로 커넥션 풀기능 작업 필요 없다. 
    - BasicDataSource는 DataSource인터페이스를 구현한 클래스다. 
    - DataSource 객체가 준비 됬으면 MemberDao에 주입을 해야 한다. memberDao.setDatasource(ds);
    - DataSource로부터 커넥션을 다쓰고 커넥션을 반납해야 되는데 코드상에 반납하는 부분이 보이지 않는다?
    - DataSource는 Connection 객체를 만들때 커넥션 대행객체(Proxy Object) PoolableConnection 객체를 리턴한다. 
    - 이 대행 객체 안에는 진짜 커넥션을 가리키는 참조변수 _conn과 커넥션 풀을 가리키는 참조변수 _pool이 있다. 
    - 그러므로 DataSource가 만들어준 커넥션 대행객체에 대해 close()를 호출하면, 커넥션 대행객체는 진짜 커넥션 객체를 커넥션풀에 반납한다. 
  
  - 서버에서 제공하는 DataSource 사용하기
    - Tomcat 폴더에 context.xml 부분에 다음을 추가 하자
      >   <Resource name="jdbc/studydb" auth="Container" type="javax.sql.DataSource"
		      maxActive="10" maxIdle="3" maxWait="1000" username="root" password="1111" 
		      driverClasName="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/studydb" closeMethod="close"/>
  - 웹 애플리케이션에서 톰캣 서버 자원 사용
    - DD파일에 서버 자원을 참조한다는 선언을 해주자.
    >   <resource-ref> 
		     <res-ref-name>jdbc/studydb</res-ref-name>
		     <res-type>javax.sql.DataSource</res-type>
		      <res-auth>Container</res-auth>
	      </resource-ref>
    - <res-ref-name> 태그 값은 context.xml에 선언하나 자원 이름. 
    - <res-type>은 톰캣 서버에서 리턴하는 자원의 타입. 
  - JNDI?
    - Java Naming and Directory Interface API의 머릿글자.
    - 이 API를 사용해 서버의 자원을 찾을 수 있따. 
    - 서버에 자원을 찾기 위해 InitialContext객체를 생성하고, lookup() 메서드를 이용해, JNDI이름으로 등록된 서버자원을 찾는다. 
    - DataSource가 만든 커넥션을 모두 닫아야 할 필요가 있는데, 톰캣 서버에 자동으로 해제하라고 설정되어 있어 따로 아무 작업 안해도 된다. 
    
# MVC 프레임워크 맨들기
## 프런트 컨트롤러
  - 프런트 컨트롤러 패턴
    >
      웹브라우저 <-> 프런트컨트롤러(DispatcherServlet) <-> 페이지 컨트롤러(Servlet)  
                       <-> 뷰(JSP)                     <->모델(DAO)
    - 1) 웹 브라우저에서 요청이 들어옴 -> 프런트컨트롤러가 받음 -> 프런트컨트롤러는 VO객체 생성해 클라이언트가 보낸 데이터 담음 -> ServletRequest에 VO객체 저장 -> 요청 ㄱURL에 따라 페이지 컨트롤러 선택 실행위임
    - 2) 페이지 컨트롤러는 DAO를 사용해 VO를 처리
    - 3) DAO는 페이지 컨트롤러 부터 받은 데이터 처리
    - 4) DAO 호출이 끝나면, 페이지 컨트롤러는 화면을 만들 떄 사용할 데이터 준비 -> JSP가 사용할 수 있도록 ServletRequest 보관소에 저장. -> 프런트 컨트롤러에게 뷰 정보 반환
    - 5) 프런트 컨트롤러는 페이지 컨트롤러가 알려준 JSP로 실행 위임 
    - 6) JSP는 페이지 컨트롤러에서 준비한 데이터를 가지고 화면 생성해 출력 -> 프런트 컨트롤러는 웹 브라우저의 요청에 대한 응답완료
    - 즉 프런트 컨트롤러는 VO 객체준비, 뷰 컴포넌트로 위임, 오류 처리등 같은 공통작업 담당
    - 페이지 컨트롤러는 요청한 페이지 작업 수행.
  -  프런트 컨트롤러 만들기
    - 프런트 컨트롤러도 서블릿이라 HttpServlet 상속 받는다. 
    - 근데 여기서 doPost, doGet을 오버라이딩 하지 않고 service()를 오버라이딩 하고 있다.
    - 이는 서블릿컨테이너(톰켓)이 직접 호출하고 있지 않다는 뜻. 
    - 왜 이렇게 했을까?
    - GET, POST 뿐만아니라 다양한 요청 방식에도 대응하기 위해서다. 
    - 요청 URL 경로를 알아내기 위함 여러 함수들이 존재 한다. 
    - 그 중 클라이언트가 요청한 서블릿 경로를 알고 싶으면 getSerbletPath()를 호출하자. 
    - 페이지 컨트롤러 위임은 RequestDispatcher를 통해 적절한 페이지 컨트롤러를 인클루딩 한다. 
    - 요청 매개 변수로 부터 VO 객체 준비
      - 페이지 컨트롤러가 필요한 데이터를 미리 준비해야 하는데, 데이터를 페이지 컨트롤러에 전달위해 요청 매개변수의 값을 꺼내 VO 객체에 담고 ServletRequest에 보관한다. 
        >   request.setAttribute("member",new Member().setName(request.getParameter("name")...));
    - JSP로 위임
      - 페이지 컨트롤러 실행이 끝나면, 화면 출력위해 SerbletRequest에 보관된 뷰 URL로 실행을 위임한다. 
      - URL이 "redirect:"로 시작하면 인클루딩 대신 sendRedirect()를 호출하자. 
    - 오류처리
      - 프런트 컨트롤러에서 오류를 처리담당하면, 페이지 컨트롤러에서 작성할 필요가 없어진다. 
    - 프런트 컨틀로러 배치
      - @WebServlet 애노테이션을 사용해 프런트 컨트롤러 배치 URL을 "*.do"로 지정한다. 
      - 서블릿 경로 이름이 .do로 끝나믄 디스패처서블릿 처리하겠다는 의미이다. 
  
  - MemberListServet을 페이지 컨트롤러로 만들기
    - JSP URL 정보를 프런트 컨트롤러에게 전달하려 SerbletRequest 보관소에 저장한다. 
      > request.setAttribute("viewUrl","/member/MemberList.jsp");
  - 프런트 컨트롤러를 통한 회원 목록 페이지 요청
  - 리다이렉트를 위한 뷰 URL 설정
    >  request.setAttribute("viewUrl","request:list.do");

## 페이지 컨트롤러의 진화
  - 기존 페이지 컨트롤러를 서블릿이 아닌 일반 클래스로 전환해 보자.
  - 일반 클래스로 만들면 재사용성이 더 높아진다. 
  - 호출 규칙 정의
    - 인터페이스는 사용자와 피사용자 사이의 일관성 있는 사용을 보장한다. 
    - 프런트컨트롤러와 페이지 컨트롤러에게 작업을 위임할때 포워드, 인클루드가 아닌 메서드 execute()를 호출한다. 
  - 프런트 컨트롤러 변경
    - Map 객체 준비
      - 프런트 컨트롤러와 페이지 컨트롤러 사이에 데이터 객체를 주고받을 때 사용할 Map 객체. 
      - MemberListController는 회원 목록을 가져오기 위해 MemberDao객체가 필요. ServletCOntext에서 가져와 담자.
      > 
        ServletContext sc = this.getServletContext();
        HashMap<String,Object> model = new HashMap<>();
        model.put("memberDao",sc.getAttribute("memberDao"));
    - 회원 목록을 처리할 페이지 컨트롤러 준비
      - 페이지 컨트롤러는 Controller의 구현체이기 때문에, 인터페이스 타입의 참조 변수를 선언하자.
      - 그래야 다른 페이지 컨트롤러(MemberAddController...) 등의 객체 주소도 저장할 수 있따. 
      >
        Controller pageConroller = null;
        if("/member/list.do".equals(servletPath))
          pageController = new MmemberListController();    
    - 페이지 컨트롤러 실행
      - 이전에는 페이지 컨트롤러가 스블릿이라 인클루딩 방법을 사용해 실행을 위임해썼다.
      - 이젠 MemberListController는 일반 클래스라 Controller 인터페이스에 정해진 대로 execute()메서드를 호출한다. 
    - Map 객체에 저장된 값을 ServletRequest에 복사
      - 페이지 컨트롤러 실행 끝난 다음, Map에 보관데어 있는 데이터나 객체를 Jsp가 사용할 수 있도록 ServletRequest에 복사하자.
      > 
        for(String key : model.keySet()) {
          request.setAttribute(key,model.get(key));
        }

## DI를 이용한 빈 의존성 관리
  - MemberListController가 작업을 수행하려면 MemberDao가 필요로한다. 
  - 이렇게 특정작업 수행할 떄 사용하는 객체를 '의존객체'라고 하고 이 관계를 '의존 관계'라 한다.
  - 의존객체 관리는 의존객체를 직접생성하는 고전적 방법과, 외부에서 의존 객체를 주입해주는 최근 방법이 있다.
  - 의존 객체 필요 시 즉시 생성하는 방법
    - 의존 객체를 사용하는 쪽에서 직접 그 객체를 생성하고 관리한다. 
    > 
      MemberDao memDao = new MemberDao(); 
      memDao.setConnection(conn);
  - 의존 객체를 미리 생성해 두었다가 필요할 때 사용하는 방법
    - MemberDao 객체를 웹 애플리케이션이 시작할때 미리 생성하여 ServletContext에 보관해둬 필요할때 꺼내 쓴다. 
    >
      ServletContext sc = this.getServletContext();
      MemberDao memDao = (MemberDao)sc.getAttribute("memberDao");
  - 의존 객체와의 결합도 증가에 따른 문제
    - 코드의 잦은 변경
    - 대체가 어려움
  - 의존 객체를 외부에서 주입하자.
    - 위와 같은 문제들이 있어서 나온 방안이다.
    - 의존 객체를 전문으로 관리하는 '빈 컨테이너'가 등장.
    - 빈 컨테이너는 객체가 실행되기 전 그 객체가 필요로 하는 의존 객체를 주입해 주는 역할을 수행한다. 
    - 위와 같은 방식을 '의존성주입(DI)'라 한다. == 역제어(IoC)
      >
        InitialContext initContext = new InitalContext();
        DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/studydb");
  - MemberListController에 MemberDao 주입
    - 의존객체 주입을 위한 인스턴스 변수와 세터 메서드
  - 페이지 컨트롤러 객체들을 준비
    - ContextLoadListener에서 contextInialized()함수에 준비해두자. 
    > sc.setAttribute("/auth/login.do",new LogInController().setMemberDao(memberDao));

## 인터페이스를 활용해 의존객체에 대해 선택의 폭을 넓히자.
  - 의존객체를 사용할 때 인터페이스를 사용하면, 그 자리에 다양한 구현체를 놓을 수 있다. 

## 리플렉션 API(여기부터 다시 정리...)
  - 리플렉션 API를 활용해 인스턴스를 자동으로 생성하고, 메서드를 자동으로 호출하도록 하자. 
  - 실행 시나리오
    1) 웹 브라우저가 회원 등록 요청 --> 사용자가 입력한 매개변수 값을 서블릿에 전달.
    2) 프런트컨트롤러(DispatcherServlet)은 회원 등록츨 처ㅣ하는 페이지 컨트롤러에게 어떤 데이터가 필요한지 물어봄 --> 페이지 컨트롤러가 필요한 데이터의 이름과 타입 저보를 담은 배열 리턴
    3) 프론트 컨트롤러는 ServletRequestDataBinder를 이용해 요청 매개변수로부터 페이지 컨트롤러가 원하는 형식의 값객체를 만듬
    4) 프런트 컨트롤러는 ServletRequestDataBinder가 만들어준 값 객체를 Map에 저장.
    5) 프런트 컨트롤러는 페이지 컨트롤러를 실행 --> 페이지 컨트롤러의 execute() 호출할 떄, 저장된 Map 객체를 매개변수로 넘김.
  - DataBinding 인터페이스 정의
    - 프런트 컨트롤러가 페이지 컨트롤러 실행전 원하는 데이터가 무엇인지 묻는 과정.
    - 페이지 컨트롤러 중 클라이언트가 보낸 데이터가 필요한 경우 DataBinding 인터페이스를 구현.
    - getDataBinders() 반환값은 데이터의 이름과 타입 정보를 담은 Object배열임.
    >
      public interface DataBinding {
	    Object[] getDataBinders();
      }
  - 페이지 컨트롤러의 DataBinding 구현
    - getDataBinders()에서 지정한 대로 프런트 컨트롤라가 VO객체를 무조건 생성할 것이기 때문에 Member가 있는지 여부를 판단하면 안된다.

# 퍼시스턴스 프레임워크(나중에 정리...)

스프링 IoC 컨테이너
스프링 IoC 컨테이너 사용준비
스프링 프레임워크에서 빈 관리 컨테이너를  IoC 컨테이너라 한다. 
1. 의존성 주입(DI)와 역제어(IoC)
역제어의 대표 사례로 이벤트가 있다. 
두번째 사례로 의존성 주입이 있다. 
어떤 작업을 처리하기 위해 사용하는 객체를 의존 객체라 한다. 
내부에서 생성하는 것이 아닌 외부에서 의존 객체를 주입하는 것.

2. XML 기반 빈 관리 컨테이너
  스프링에선 자바 객체를 ‘빈(Bean)’이라 한다. 그래서 객체관리 컨테이너를 ‘빈 컨테이너'라 부른다. IoC 컨테이너는 두 가지 방식으로 처리할 수 있는데, XML과 애노테이션 방법이다.
2.1 ApplicationContext 인터페이스 
스프링은 IoC 컨테이너가 갖추어야 할 기능들을 ApplicationContext 인터페이스에 정의해 뒀다. 
자식들로 ClassPathXmlApplicationContext, FileSystemXmlApplicationContext, WebApplicationContext가 있다.
ClassPathXmlApplicationContext은 자바 클래스 경로에서  XML로 된 빈 설정 파일을 찾는다.
FileSystemXmlApplicationContext은 파일 시스템 경로에서 빈 설정 파일을 찾는다.
WebApplicationContext는 웹 어플리케이션을 위한 IoC컨테이너로 web.xml 파일에 설정된 정보에 따라 XML을 찾는다. 
2.2 스프링 빈 컨테이너 ClassPathXmlApplicationCOntext
Product 클래스 정의
빈컨테이너에서 관리할 수 있는 클래스를 만든다. 상품이름, 가격, 재고를 보관
값을 보관하는 용도로 사용하는 클래스를 ‘값객체(VO)’라 부른다. 
빈 설정 XML 파일 준비
애플리케이션에서 사용할 객체는 빈 설정 파일에 선언한다. 
스프링 IoC 컨테이너는 빈 설정 파일에 선언된 대로 빈을 생성한다. 
	>  <bean id=”score” class=”com.exam.Product”/> </bean>
위는 자바 코드로한다면 new com.exam.Product(); 정도 되겠다. 
  
스프링 IoC 컨테이너 사용
IoC 컨테이너는 빈 설정 파일에 선언된 대로 인스턴스를 생성해 객체 풀에 보관한다. 
public class Test {
    public static void main(String[] args) {
// 생성자에서 빈 설정 파일의 경로를 넘긴다.
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("com/exam/beans.xml");
Product product = (Product) ctx.getBean("product");
        
        System.out.println("합계:" + pruduct.sum());
        System.out.println("가격:" + product.price);
    }
}

빈 컨테이너가 준비 되면 getBean()을 호출해 빈을 꺼낸다. 인자로는 id, name값이옴
getBean() 반환타입은 Object라 형변환 해야 한다. 

2.3 name 속성으로 빈 이름 지정
<bean> 태그를 사용해 자바 빈 설정 할 때 id 대신 name을 사용할 수 있따. 
id , name 차이점?




id속성
name 속성
용도
빈 식별자 지정. 중복 안됨.
인스턴스의 별명 추가할때 사용. 중복 안됨
여러 개 이름 지정
불가
콤마, 세미콜론, 공백으로 여러개 지정 가능.
첫 번쨰 이름은 컨테이너에 빈을 보관할 때 사용, 나머지는 빈의 별명

2.4 익명 빈 선언
빈 선언 시  id, name값을 지정하지 않는다. 
컨테이너에 보관할 때 ‘패키지이름 + 클래스이름 + #인덱스'를 빈의 이름으로 사용한다. 
인덱스는 0부터 시작한다. 
getBean()의 인자로 클래스 타입이 오면, 같은 타입이 여러개가 있으면 에러가 난다. 

3. 생성자와 프로퍼티 설정
3.1 호출할 생성자 설정
빈선언 시 <constructor-arg>를 이용해 호출될 생성자를 지정할 수 있다. 
>
	<bean id="score1" class="exam.test04.Score">
     <constructor-arg><value type="java.lang.String">오세진</value></constructor-arg>
     <constructor-arg><value type="float">91</value></constructor-arg>
     <constructor-arg><value type="float">92</value></constructor-arg>
     <constructor-arg><value type="float">93</value></constructor-arg>
     </bean>
public Score(String name, float kor, float eng, float matn) {...} 라는 생성자가 VO에 있을 때 <construct-arg> 태그를 사용해 호출될 생성자를 지정할 수 있다. 
<construct-arg value=”홍길동”/>  이런식으로 지정할 수 도 있다. 
3.2 프로퍼티 설정
빈 설정에서 <propery>를 사용해 지정해 주자.
프로퍼티란 클래스에서 겟터/셋터 메서드를 가리키는 용어이다. 
>
	<bean id="score1" class="exam.test05.Score">
   	<property name="name"><value>오세진</value></property>
   	<property name="kor"><value>100</value></property>
   	<property name="eng"><value>95</value></property>
   	<property name="math"><value>90</value></property>
 	</bean>
<property name=”name” value=”오세진"/> 이 형식도 가능하다. 
java 표현으론 Score score = new Score();  score.setName(“오세진"); 이 된다. 

3.3 <bean>의 속성 이용해 생성자 / 프로퍼티 설정
<propert>, <construt-arg>를 사용안하고 다른 방법이 있다.
네임스페이스 ‘p’, ‘c’를 저정해서 사용하면 된다. 
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
  <bean id=”socre1” class=”exam.test.Score” p:name=” 오세진" p:kor=”100” p:eng=”100” p:math=”100/>

4. 의존객체 주입
보통 지속적으로 사용할 객체는 프로퍼티에 보관한다. 
4.1 의존객체 설정
  <bean id=”engine” class=”exam.test.Engine” c:maker=”HD” p:cc=”2018”/>
  <bean id=”car1” class=”exam.text.Car” />
	<property name=”model” value=”Sonata”/>
	<property name=”engine” ref=”engine”/>
  </bean>
engine 프로퍼티의 값은 Engine의 레퍼런스어야 한다. 그래서 <ref>를 썻다. 
빈의 기본 프로포터값은 <value>를 사용하고, 빈의 레퍼런스는 <ref> 속성을 사용한다. 
‘p’,’c’를 이용해 할수 도 있다.
기본타입은 ‘p:프로퍼티이름', 레퍼런스타입은 ‘p:프로퍼티이름-ref’로 지정한다. 
생성자 매개변수 설정할 때 새로 빈을 생성해 할당 하고 싶다면 <construct-arg>의 자식 태그로 <bean>을 선언하면 된다.  

5. 컬렉션 값 주입
5.1 배열 프로퍼티 값 주입
 >
	<bean id="car1" class="exam.test09.Car">
        <constructor-arg value="Avante" />
        <constructor-arg>
            <bean class="exam.test09.Engine" p:maker="Hyundai" p:cc="1495" />
        </constructor-arg>
        <property name="tires">
            <list>
                <bean class="exam.test09.Tire" p:maker="Kumho" p:spec="P185/65R14" />
                <bean class="exam.test09.Tire" p:maker="Kumho" p:spec="P185/65R14" />
                <bean class="exam.test09.Tire" p:maker="Hankook" p:spec="P205/65R14" />
                <bean class="exam.test09.Tire" p:maker="Hankook" p:spec="P205/65R14" />
            </list>
        </property>
    </bean>
배열이나 List 타입의 프로퍼티를 설정할 땐 <list> 태그를 사용한다. 
간단한 상수값을 넣을떄는 <value>, 다른 빈의 래퍼런스를 추가할 떈 <ref bean> 새로운 빈을 넣을떈 <bean> 태그를 사용한다. 
set 타입은 기존에 등록된 객체와 값이 같은지 조사하여 같지 않을 경우에만 Set에 추가한다. <set>  속성을 활용해 list와 같은 방식으로 사용하면 된다. 

5.2 Map과 Properties 값 주입
맵과 프로퍼티는 key, value 한 쌍을 묶어 저장한다. 
Properties 타입의 값을 설정할 떈 <props> 태그를 사용한다. Properties에 저장할 항목은 <prop> 태그로 정의한다. 
Map에서 <key>태그를 사용하는데 <key> 태그엔 바로 값을 넣을 수 없어서 <value>를 사용해 넣는다. 

6. 팩토리 매서드와 팩토리 빈
공장 역할을 통해 인스턴스를 얻는 빈 설정 방법이다.
인스턴스 생성작업이 복잡한 경우 매번 생성(new)하기 부담 스럽다. 이를 해결하려 팩토리 메서드 패턴과 빌더 패턴이다. 
팩토리 클래스는 인스턴스 생성 과정을 캡슐화 한다. 
6.1 스태틱 팩토리 메서드를 이용한 간접 객체 생성
팩토리 메서드 만들 떄 두가지 방법이 있다.
스태틱으로 선언해 클래스 메서드로 만들거나, 인스턴스 메서드로 만드는 방법이 있다.
스태틱 팩토리 만드는 방법을 먼저 보자
>
	public static Tire createTire(String maker) {
        if (maker.equals("Hankook")) {
            return createHankookTire();
        } else {
            return createKumhoTire();
        }
    }
    
    private static Tire createHankookTire() {
        Tire tire = new Tire();
        tire.setMaker("Hankook");
        
        Properties specProp = new Properties();
        specProp.setProperty("width", "205");
        specProp.setProperty("ratio", "65");
        specProp.setProperty("rim.diameter", "14");
        tire.setSpec(specProp);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tire.setCreatedDate(dateFormat.parse("2018-7-5"));
        } catch (Exception e) {}
        
        return tire;
    }

createTire()는 팩토리 메서드이다. 일반 클래스의 메서드를 팩토리 기능을 하도록 하려면 반드시 static으로 선언해야 한다. 
팩토리 클래스를 사용하여 빈을 생성해 보자
  >
	<bean id=”hankookTire” class=”exam.test.TireFactory” factory-method=”createTire”>
	  <constructor-arg value=”Hankook”/>
	</bean>
   	⇒ 자바로는 Tire hankookTire = TireFctory.createTire(“Hanook”);
getBean()은 호출될 때마다 팩토리 메서드를 호출하지 않는다. 
처음 빈을 만들 떄 딱 한번 호출한다. 같은 이름으로 빈을 꺼내면 같은 객체로 인식한다.
6.2 인스턴스 팩토리 메서드를 이용한 간접 객체 생성
앞에서 만든  TireFactory 클래스에서 모든 메서드의  static  선언을 제거
beans.xml에서
 >
	<bean id=”tireFatory” class=”exam.test.TireFactory/>
	<bean id=”hankookTire” factory-bean=”tireFactory” factory-method=”createTire”>
	    <constructor-arg value=”Hankook”/>
	</bean>
	⇒ Java로 TireFactory tireFactory = new TireFactory();
	                Tire hankkokTire = tireFactory.createTire(“Hanook”);
factory-bean 속성에 팩토리 객체를 지정한다. 
class속성은 지정하지 않는다. 
factory-method 속성엔 인스턴스 팩토리 메서드 이름을 지정한다. 스테틱 메서드는 안됨.
팩토리 메서드 매개 변수는 <constructor-arg> 태그로 지정한다. 
6.3 스프링 규칙에 따라 팩토리 빈을 만들어보자
팩토리빈 클래스를 만들 때 FactoryBean 인터페이스를 구현하자. 
보통은 FactoryBean을 직접 구현하지 않고 미리 구현된 AbstractFactoryBean을 상속해 구현한다.
빈 생성할떄 팩토리 메서드로 getObject()가 호출되는데, 이 메서드 내부적으로 createInstance() 추상메서드가 호출된다. 
AbstractFactoryBean를 상속받았다면 createInstance()메서드를 바느시 구현하자. 
getObjetctType()도 반드시 구현하자. 
AbstracFactoryBean<>은 다양한 타입의 빈을 받을 수 있는 제네릭 문법이 적용됬다. 
스프링 IoC 컨테이너는 class 속성에 주어진 클래스가 일반클래스가 아니라 FactoryBean 타입의 클래스일 경우, 이 클래스의 인스턴스를 직접 보관하지 않고, 이 클래스가 생성한 빈을 컨테이너에 보관한다. 

7. 빈의 범위 설정
스프링 IoC 컨테이너는 빈을 생성할 때 기본적으로 한개만 생성한다. 
getBean()을 반복해도 동일한 객체가 반환된다. 


범위
설명
singleton
오직 하나의 빈만 생성(기본)
prototype
getBean() 호출할 때마다 빈 생성
request
HTTP 요청이 발생할 때마다 생성. 웹 애플리케이션에서만 적용
session
HTTP 세션이 생성될 때마다 빈 생성. 웹 애플리케이션에서만 적용
globalsession
전역세션이 준비될 떄 빈 생성. 웹 애플리케이션에서만 적용
7.1 싱글톤과 프로토타입
  >
	<bean id="hyundaiEngine" class="exam.test14.Engine"
 	   p:maker="Hyundai" p:cc="1997"/>
    
<bean id="kiaEngine" class="exam.test14.Engine"
 	   p:maker="Kia" p:cc="3000"
 	   scope="prototype"/>
빈의 범위를 설정하지 않으면 ‘singleton’이 기본 범위로 지정 된다.
scope=”prototype”이면 빈 컨테이너에게 기아엔진을 요청할 때마다 새 Engine인스턴스를 만든다.

8. 애노테이션을 이용한 의존 객체 자동 주입
빈의 세터 메서드에 @Autowired를 선언하면 빈 컨테이너가 셋터 매개변수 타입과 일치하는 빈을 찾아 자동으로 설정해 준다. 
@Autowired를 선언하면 해당 프로퍼티는 필수 입력항목이 된다. 
required속성을 이용해 false로 바꾸면 필수가 아닌 선택으로 된다. 
@ Autowired(required=fase)
8.3 @Qualifier로 주입할 객체를 지정하기
@Autowired는 프로퍼티에 주입할 수 있는 의존객체가 여러개 있을 경우 오류를 발생한다. 
같은 타입이 여러개 있으면 어떤걸 넣을지 혼란하기 때문이다. 
@Autowired와 함꼐 @Qalifier로 빈의 이름(id)로 의존 객체를 지정할 수 잇다. 
8.4 @Resource = @Autowired + @Qualifier
@Resource(name=”kiaEngine”)

9. 빈 자동 등록
빈 생성 대상이 되는 클래스에 @Component 애노테이션으로 표시한다. 
스프링 IoC 컨테이너는 @Comconent가 붙은 클래스를 찾아 빈을 생성한다. 
애노테이션 정보


애노테이션
설명
@Component
빈 생성 대상이 되는 모든 클래스에 붙일 수 있다.
@Repository
DAO와 같은 persistence 역할을 수행하는 클래스에 붙인다. 
@Service
서비스역할을 수행하는 클래스에 붙인다.
@Controller
MVC 구조에서 Controller 역할을 수행하는 클래스에 붙인다.
web.xml에 <context:component-scan base-package=”exam.test”/>를 추가하자.
위 태그는 @Component, @Repository등 빈 생성 표시자가 붙은 클래스를 검색한다.
위를 설정하면 <context:annatation-confg> 태그가 자동 활성화 된다. 


#Gradle
## 그래들이란?
  - 스프링 프레임워크를 사용하려면 그와 관련된 라이브러리 파일이 필요하다.
  - '동적 웹 프로젝트'에선 관련 사이트로 부터 라이브러리 파일을 일일이 받아야한다.
  - 하지만 '메이븐', '그래들' 같은 전문 프로젝트 빌드 도구를 사용하면 번거로움이 사라진다. 

## Gradle 프로젝트 파일 및 폴더 구조
  - .gradle : Gradle 빌드 도구와 관련된 파일을 두는 폴더
  - .settings : 이클립스와 관련된 설정 파일을 두는 폴더
  - bin : 컴파일된 자바 클래스 파일을 두는 폴더
  - build : Gradle 빌드의 실행 결과물이 놓이는 폴더
  - src/main/java : 자바 소스파일을 두는 폴더
  - src/main/resources : 애플리케이션이 참조하는 파일을 두는 폴더
  - src/test/java : 단위테스트 관련 자바 소스 파일을 두는 폴더
  - src/test/resources : 단위 테스트를 수행할 때 참조하는 파일을 두는 폴더
  - .classpath : 이클립스가 침조하는 클래스 경로 설정 파일
  - .project 이클립스 프로젝트 설정 파일
  - build.gralde : Gradle 빌드 설정 파일
  
## Gradle 프로젝트를 웹 프로젝트로
  - 이클립스는 가사 생성된 Gradle 프로젝트를 자바 프로젝트로 인식 함. 
  - 웹 프로젝트 관련 파일을 생성해야 하는데 gradle을 이용하면 쉽게 처리 할 수 있다.

  



          
