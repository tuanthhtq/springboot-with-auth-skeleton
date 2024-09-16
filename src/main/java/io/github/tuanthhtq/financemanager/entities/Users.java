package io.github.tuanthhtq.financemanager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/**
 * @author io.github.tuanthhtq
 */

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users {

	@Id
	private String id;
	private String username;
	private String password;
	private String displayName;

	@DocumentReference(lazy = true, collection = "money")
	private List<Money> moneys;
}
