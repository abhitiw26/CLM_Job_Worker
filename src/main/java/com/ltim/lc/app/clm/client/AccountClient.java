package com.ltim.lc.app.clm.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ltim.lc.app.clm.client.AccountClient.AccountClientFallBack;
import com.ltim.lc.app.clm.model.AccountDetails;
import com.ltim.lc.app.clm.model.AccountUpdate;

import feign.FeignException;

@FeignClient(name = "${account.service}", url = "${account.url}", fallbackFactory = AccountClientFallBack.class)
public interface AccountClient { 

	@PostMapping(path = "${account.paths.createAccount}")
	public ResponseEntity<?> createAccount(@RequestBody AccountDetails accountDetails);
	
	@GetMapping(path = "${account.paths.getAccount}")
	public ResponseEntity<?> getAccount(@PathVariable String id);
	
	@PutMapping(path = "${account.paths.updateAccount}")
	public ResponseEntity<?> updateAccount(@RequestBody AccountUpdate accountUpdate,@PathVariable String id);
	

	@Component
	class AccountClientFallBack implements FallbackFactory<AccountClient> {


		public AccountClient create(Throwable ex) {
			int httpStatus = 0;
			String error = "";
			if (ex instanceof FeignException) {
				httpStatus = ((FeignException) ex).status();
				error = ex.getMessage();
			} else {
				error = ex.getMessage();
			}
			final int status = httpStatus;
			final String errorMessage = error;
			return new AccountClient() {
				@Override
				public ResponseEntity<?> createAccount(AccountDetails accountDetails) {
					if (status > 0) {
						return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(status));
					} else {
						return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
					}
				}
				@Override
				public ResponseEntity<?> getAccount(String id) {
					if (status > 0) {
						return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(status));
					} else {
						return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
					}
				}

				@Override
				public ResponseEntity<?> updateAccount(AccountUpdate accountUpdate,String id) {
					if (status > 0) {
						return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(status));
					} else {
						return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
					}
				}

			};

		}

	
	}
}
