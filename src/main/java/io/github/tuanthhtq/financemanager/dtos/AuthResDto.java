package io.github.tuanthhtq.financemanager.dtos;

/**
 * @author io.github.tuanthhtq
 */

public record AuthResDto(
		String username,
		String displayName,
		String accessToken
) {
}
