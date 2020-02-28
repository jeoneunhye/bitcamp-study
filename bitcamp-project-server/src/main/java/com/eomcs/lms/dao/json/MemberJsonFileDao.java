package com.eomcs.lms.dao.json;
// MemberDao 인터페이스를 구현
// Json 파일의 member데이터를 읽고 쓰는 기능을 하는 클래스
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberJsonFileDao extends AbstractJsonFileDao<Member> implements MemberDao {
  public MemberJsonFileDao(String filename) {
    super(filename);
  }

  // 서블릿 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(Member member) throws Exception {
    if (indexOf(member.getNo()) > -1) {
      return 0;
    }

    list.add(member);
    saveData();

    return 1;
  }


  public List<Member> findAll() throws Exception {
    return list;
  }

  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public int update(Member member) throws Exception {
    int index = indexOf(member.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, member);
    saveData();
    System.out.println("데이터를 저장합니다.");

    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    System.out.println("데이터를 저장합니다.");

    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) { // key가 어떤 타입인지 형변환 필요
        return i;
      }
    }

    return -1;
  }
}