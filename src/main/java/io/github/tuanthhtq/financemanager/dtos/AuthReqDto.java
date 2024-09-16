package io.github.tuanthhtq.financemanager.dtos;

/**
 * @author io.github.tuanthhtq
 */

public record AuthReqDto
		(
				String username,
				String password,
				String passwordConfirmation,
				String displayName
		) {
}
