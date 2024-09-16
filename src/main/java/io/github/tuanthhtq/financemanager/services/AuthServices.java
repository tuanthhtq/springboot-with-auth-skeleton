package io.github.tuanthhtq.financemanager.services;

import io.github.tuanthhtq.financemanager.dtos.AuthReqDto;
import io.github.tuanthhtq.financemanager.dtos.AuthResDto;
import io.github.tuanthhtq.financemanager.dtos.Response;

/**
 * @author io.github.tuanthhtq
 */

public interface AuthServices {

	Response<AuthResDto> login(AuthReqDto req);

	Response<AuthResDto> register(AuthReqDto req);
}
