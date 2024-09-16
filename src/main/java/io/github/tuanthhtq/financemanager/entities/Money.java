package io.github.tuanthhtq.financemanager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Date;

/**
 * @author io.github.tuanthhtq
 */

@Document(collection = "money")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Money {

	@Id
	private String id;

	private int type;

	@DocumentReference(collection = "users", lazy = true)
	private String userId;

	private int amount;

	private String reason;
	
	@CreatedDate
	private Date createdAt;


}
