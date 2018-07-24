package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO,MNAME,EMAIL,CRE_DATE" + " FROM MEMBERS" + " ORDER BY MNO ASC");

			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE")));
			}

			return members;

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public int insert(Member member) throws Exception {

		try (PreparedStatement psmt = connection.prepareStatement(
				"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)" + " VALUES (?,?,?,NOW(),NOW())");) {
			psmt.setString(1, member.getEmail());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getName());

			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public int delete(int no) throws Exception {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO=" + no);

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Member selectOne(int no) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + " WHERE MNO=" + no);
			if (rs.next()) {
				return new Member().setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME")).setCreatedDate(rs.getDate("CRE_DATE"));

			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public int update(Member member) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()" + " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Member exist(String email, String password) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.prepareStatement("SELECT MNAME,EMAIL FROM MEMBERS" + " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member().setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

}
