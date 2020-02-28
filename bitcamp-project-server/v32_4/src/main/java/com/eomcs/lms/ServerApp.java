// LMS 서버
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  public void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  public void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {
    notifyApplicationInitialized();

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        if (processRequest(socket) == 9) {
          break;
        }

        System.out.println("-----클라이언트 요청 처리 끝-----");
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();
  }

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try(
        Socket socket = clientSocket;

        // Scanner in = new Scanner(socket.getInputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // list 객체를 통째로 가져와 명령를 처리하기 때문에 ObjectInputStream으로 도구 변경

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        // String request = in.nextLine();
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지: " + request);
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();

          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();

          return 9;
        }

        List<Lesson> lessons = (List<Lesson>) context.get("lessonList");
        List<Member> members = (List<Member>) context.get("memberList");
        List<Board> boards = (List<Board>) context.get("boardList");

        // Client의 command가 "/board/list"면
        // command 인터페이스를 구현한 BoardListCommand 구현 객체를 생성하고
        // ClientApp이 BoardListCommand의 execute()를 호출!
        // BoardListCommand: out.writeUTF("/board/list");
        if (request.equals("/board/list")) {
          out.writeUTF("OK");

          out.reset(); // disregard already written to the stream
          // 기존에 출력했던 List<Board> 객체의 직렬화 데이터를 무시하고
          // 새로 직렬화를 수행한다.
          // reset을 해야 add한 board 객체가 list에 정상적으로 출력된다.

          // out.writeObject(context.get("boardList"));
          out.writeObject(boards); // Board가 담긴 List 객체를 통째로 출력
          // BoardAddCommand: out.writeUTF("/board/add");
        } else if (request.equals("/board/add")) {
          try {
            // 클라이언트에서 저장한, Board 객체를 통째로 읽는다.
            Board board = (Board) in.readObject();

            int i = 0;
            for (; i < boards.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (boards.get(i).getNo() == board.getNo()) {
                // List의 i번째 Board 객체의 번호와 저장한 Board 객체의 번호와 같다면
                break;
              }
            }

            // break되지 않았을 때 (클라이언트가 보낸 객체와 같은 번호를 가진, 객체를 못 찾았을 때)
            if (i == boards.size()) {
              boards.add(board); // 새 게시물을 등록한다.
              System.out.println("게시물을 저장하였습니다.");

              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시물이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // BoardDetailCommand: out.writeUTF("/board/detail");
        } else if (request.equals("/board/detail")) {
          try {
            int no = in.readInt(); // BoardDetailCommand: out.writeInt(no);

            Board board = null;
            for (Board b : boards) {
              if (b.getNo() == no) { // Board 객체 중에 입력받은 no와 번호가 같은 객체가 있다면
                board = b; // 빈 board 객체에 해당 b 값을 넣는다.
                break;
              }
            }

            if (board != null) { // board의 값이 생겼다면(같은 번호를 가진 객체를 찾았다면)
              out.writeUTF("OK");
              out.writeObject(board); // 값이 든 board 객체를 통째로 출력한다.

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // BoardUpdateCommand: out.writeUTF("/board/update");
        } else if (request.equals("/board/update")) {
          try {
            // 클라이언트에서 변경 처리한, Board 객체를 통째로 읽는다.
            Board board = (Board) in.readObject();

            // 변경한 board 객체의 번호로 기존 List의 몇번째 board 객체였는지 자리를 찾는 과정!
            int index = -1;
            for (int i = 0; i < boards.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (boards.get(i).getNo() == board.getNo()) {
                // List의 i번째 Board 객체의 번호와 변경한 Board 객체의 번호와 같다면
                index = i; // 그 i값을 index로 넣는다.
                break;
              }
            }

            if (index != -1) { // 기존의 List에서 같은 번호를 가진 객체를 못 찾았다면
              boards.set(index, board); // List 객체에 변경된 객체를 저장(index번째 board로 새로 교체)
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // BoardDeleteCommand: out.writeUTF("/board/delete");
        } else if (request.equals("/board/delete")) {
          try {
            int no = in.readInt(); // BoardDeleteCommand: out.writeInt(no);

            int index = -1;

            for (int i = 0; i < boards.size(); i++) { // List의 size번까지 반복문을 돌아
              if (boards.get(i).getNo() == no) { // 삭제하려는 번호와 일치하는 i번째 board 객체를 찾았다면
                index = i; // index에 해당 i값을 넣는다.
                break;
              }
            }

            if (index == -1) { // 삭제하려는 번호의 게시물을 찾았다면
              boards.remove(index); // List 객체 boards에서 해당 index번째 객체를 삭제
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        }
        // MemberListCommand: out.writeUTF("/member/list");
        else if (request.equals("/member/list")) {
          out.writeUTF("OK");
          out.reset(); // disregard already written to the stream
          // 기존에 출력했던 List<Member> 객체의 직렬화 데이터를 무시하고
          // 새로 직렬화를 수행한다.
          // reset을 해야 add한 member 객체가 list에 정상적으로 출력된다.

          // out.writeObject(context.get("memberList"));
          out.writeObject(members); // Member가 담긴 List 객체를 통째로 출력
          // MemberAddCommand: out.writeUTF("/member/add");
        } else if (request.equals("/member/add")) {
          try {
            // 클라이언트에서 저장한, Member 객체를 통째로 읽는다.
            Member member = (Member) in.readObject();

            int i = 0;
            for (; i < members.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (members.get(i).getNo() == member.getNo()) {
                // List의 i번째 Member 객체의 번호와 저장한 Member 객체의 번호와 같다면
                break;
              }
            }

            // break되지 않았을 때 (클라이언트가 보낸 객체와 같은 번호를 가진, 객체를 못 찾았을 때)
            if (i == members.size()) {
              members.add(member); // 새 회원을 등록한다.
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 회원이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // MemberDetailCommand: out.writeUTF("/member/detail");
        } else if (request.equals("/member/detail")) {
          try {
            int no = in.readInt(); // MemberDetailCommand: out.writeInt(no);

            Member member = null;
            for (Member m : members) {
              if (m.getNo() == no) { // Member 객체 중에 입력받은 no와 번호가 같은 객체가 있다면
                member = m; // 빈 member 객체에 해당 m 값을 넣는다.
                break;
              }
            }

            if (member != null) { // member의 값이 생겼다면(같은 번호를 가진 객체를 찾았다면)
              out.writeUTF("OK");
              out.writeObject(member); // 값이 든 member 객체를 통째로 출력한다.

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // MemberUpdateCommand: out.writeUTF("/member/update");
        } else if (request.equals("/member/update")) {
          try {
            // 클라이언트에서 변경 처리한, Member 객체를 통째로 읽는다.
            Member member = (Member) in.readObject();

            // 변경한 member 객체의 번호로 기존 List의 몇번째 member 객체였는지 자리를 찾는 과정!
            int index = -1;
            for (int i = 0; i < members.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (members.get(i).getNo() == member.getNo()) {
                // List의 i번째 Member 객체의 번호와 변경한 Member 객체의 번호와 같다면
                index = i; // 그 i값을 index로 넣는다.
                break;
              }
            }

            if (index != -1) { // 기존의 List에서 같은 번호를 가진 객체를 못 찾았다면
              members.set(index, member); // List 객체에 변경된 객체를 저장(index번째 board로 새로 교체)
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // MemberDeleteCommand: out.writeUTF("/member/delete");
        } else if (request.equals("/member/delete")) {
          try {
            int no = in.readInt(); // MemberDeleteCommand: out.writeInt(no);

            int index = -1;
            for (int i = 0; i < members.size(); i++) { // List의 size번까지 반복문을 돌아
              if (members.get(i).getNo() == no) { // 삭제하려는 번호와 일치하는 i번째 board 객체를 찾았다면
                index = i; // index에 해당 i값을 넣는다.
                break;
              }
            }

            if (index != -1) { // 삭제하려는 번호의 회원을 찾았다면
              members.remove(index); // List 객체 members에서 해당 index번째 member 객체를 삭제
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // LessonListCommand: out.writeUTF("/lesson/list");
        } else if (request.equals("/lesson/list")) {
          out.writeUTF("OK");
          out.reset(); // disregard already written to the stream
          // 기존에 출력했던 List<Lesson> 객체의 직렬화 데이터를 무시하고
          // 새로 직렬화를 수행한다.
          // reset을 해야 add한 lesson 객체가 list에 정상적으로 출력된다.

          // out.writeObject(context.get("lessonList"));
          out.writeObject(lessons); // lesson이 담긴 List 객체를 통째로 출력
          // LessonAddCommand: out.writeUTF("/lesson/add");
        } else if (request.equals("/lesson/add")) {
          try {
            // 클라이언트에서 저장한, Lesson 객체를 통째로 읽는다.
            Lesson lesson = (Lesson) in.readObject();

            int i = 0;
            for (; i < lessons.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (lessons.get(i).getNo() == lesson.getNo()) {
                // List의 i번째 Lesson 객체의 번호와 저장한 Lesson 객체의 번호와 같다면
                break;
              }
            }

            // break되지 않았을 때 (클라이언트가 보낸 객체와 같은 번호를 가진, 객체를 못 찾았을 때)
            if (i == lessons.size()) {
              lessons.add(lesson); // 새 수업을 등록한다.
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 수업이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // LessonDetailCommand: out.writeUTF("/lesson/detail");
        } else if (request.equals("/lesson/detail")) {
          try {
            int no = in.readInt(); // LessonDetailCommand: out.writeInt(no);

            Lesson lesson = null;
            for (Lesson l : lessons) {
              if (l.getNo() == no) { // Lesson 객체 중에 입력받은 no와 번호가 같은 객체가 있다면
                lesson = l; // 빈 lesson 객체에 해당 l 값을 넣는다.
                break;
              }
            }

            if (lesson != null) { // lesson의 값이 생겼다면(같은 번호를 가진 객체를 찾았다면)
              out.writeUTF("OK");
              out.writeObject(lesson); // 값이 든 lesson 객체를 통째로 출력한다.

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 수업이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // LessonUpdateCommand: out.writeUTF("/lesson/update");
        } else if (request.equals("/lesson/update")) {
          try {
            // 클라이언트에서 변경 처리한, Lesson 객체를 통째로 읽는다.
            Lesson lesson = (Lesson) in.readObject();

            // 변경한 lesson 객체의 번호로 기존 List의 몇번째 lesson 객체였는지 자리를 찾는 과정!
            int index = -1;
            for (int i = 0; i < lessons.size(); i++) { // List 객체의 size까지 반복문을 돌아
              if (lessons.get(i).getNo() == lesson.getNo()) {
                // List의 i번째 Lesson 객체의 번호와 변경한 Lesson 객체의 번호와 같다면
                index = i; // 그 i값을 index로 넣는다.
                break;
              }
            }

            if (index != -1) { // 기존의 List에서 같은 번호를 가진 객체를 못 찾았다면
              lessons.set(index, lesson); // List 객체에 변경된 객체를 저장(index번째 lesson으로 새로 교체)
              out.writeUTF("OK");

            } else { // 같은 번호를 가진 객체를 찾지 못했다면
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 수업이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
          // LessonDeleteCommand: out.writeUTF("/lesson/delete");
        } else if (request.equals("/lesson/delete")) {
          try {
            int no = in.readInt(); // LessonDeleteCommand: out.writeInt(no);

            int index = -1;
            for (int i = 0; i < lessons.size(); i++) { // List의 size번까지 반복문을 돌아
              if (lessons.get(i).getNo() == no) { // 삭제하려는 번호와 일치하는 i번째 lesson 객체를 찾았다면
                index = i; // index에 해당 i값을 넣는다.
                break;
              }
            }

            if (index != -1) { // 삭제하려는 번호의 수업을 찾았다면
              lessons.remove(index); // List 객체 lessons에서 해당 index번째 객체를 삭제
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 수업이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리하지 못했습니다.");
        }

        out.flush(); // 성공 여부에 상관없이 flush() 호출하기
        // TCP/IP에 패킷이 꽉 차있지 않아도 내보냄

      } // while문 끝
      System.out.println("클라이언트로 메시지를 전송하였음!");
      return 0;

    } catch(Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}