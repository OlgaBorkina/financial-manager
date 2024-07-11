package project.communication.dto;

import java.util.Set;

import lombok.Getter;


@Getter
public class BriefcaseDto {
	
	String nameCase;
	Set<CardDto> cards;
//	String login;
	Set<WalletDto> wallets;
	String nameManager;

}
