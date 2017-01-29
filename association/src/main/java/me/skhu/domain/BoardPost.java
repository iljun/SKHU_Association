package me.skhu.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "board_post")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPost extends BaseEntity implements Serializable{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@NotNull
	@Column(name = "title")
	private String title;

	@Lob
	@NotNull
	@Column(name = "content")
	//TODO 조금더 생각해보기
	private String content;

	@NotNull
	@Column(name = "own_board_id")
	private int ownBoardId;

	@NotNull
	@Column(name = "writer_id")
	private int writer_id;

	@NotNull
	@Column(name = "writer_name")
	private String writer_name;

	@OneToMany(mappedBy="boardPost")
	@Basic(fetch=FetchType.LAZY)
	private Collection<Comment> commentList;

	public static BoardPost ofCreate(String title, String content, int boardId, int writer_id ,String writer_name){
		return BoardPost.builder()
				.title(title)
				.content(content)
				.ownBoardId(boardId)
				.writer_id(writer_id)
				.writer_name(writer_name)
				.build();
	}

	public static BoardPost ofUpdate(int id,String title, String content, int boardId, int writer_id ,String writer_name){
		return BoardPost.builder()
				.id(id)
				.title(title)
				.content(content)
				.ownBoardId(boardId)
				.writer_id(writer_id)
				.writer_name(writer_name)
				.build();
	}

	public BoardPost(String title, String content){
		this.title=title;
		this.content=content;
	}
}