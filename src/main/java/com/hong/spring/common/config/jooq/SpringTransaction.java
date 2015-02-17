package com.hong.spring.common.config.jooq;

import org.jooq.Transaction;
import org.springframework.transaction.TransactionStatus;

class SpringTransaction implements Transaction {
	
	final TransactionStatus tx;

	SpringTransaction(TransactionStatus tx) {
		this.tx = tx;
	}
	
}