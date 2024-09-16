package io.github.tuanthhtq.financemanager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author io.github.tuanthhtq
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response<T> {

	private String message;
	private Map<String, String> error;
	private T data;
}
