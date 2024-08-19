package project.communication.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class BriefcaseHalfDto {
	String briefcaseName;
	Set<WalletDto> wallets;
	String managerName;
	Double plusToMyCash;
	String currencyCode;
	CardDto cardUsed;
}
