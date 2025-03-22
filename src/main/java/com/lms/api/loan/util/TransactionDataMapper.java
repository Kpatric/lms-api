package com.lms.api.loan.util;

import com.lms.api.loan.dto.TransactionDataDTO;
import com.lms.generated.TransactionData;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;

@Component
public class TransactionDataMapper {
    public TransactionDataDTO toDto(TransactionData tx) {
        TransactionDataDTO dto = new TransactionDataDTO();
        dto.setAccountNumber(tx.getAccountNumber());
        dto.setAlternativechanneltrnscrAmount(tx.getAlternativechanneltrnscrAmount());
        dto.setAlternativechanneltrnscrNumber(tx.getAlternativechanneltrnscrNumber());
        dto.setAlternativechanneltrnsdebitAmount(tx.getAlternativechanneltrnsdebitAmount());
        dto.setAlternativechanneltrnsdebitNumber(tx.getAlternativechanneltrnsdebitNumber());
        dto.setAtmTransactionsNumber(tx.getAtmTransactionsNumber());
        dto.setAtmtransactionsAmount(tx.getAtmtransactionsAmount());
        dto.setBouncedChequesDebitNumber(tx.getBouncedChequesDebitNumber());
        dto.setBouncedchequescreditNumber(tx.getBouncedchequescreditNumber());
        dto.setBouncedchequetransactionscrAmount(tx.getBouncedchequetransactionscrAmount());
        dto.setBouncedchequetransactionsdrAmount(tx.getBouncedchequetransactionsdrAmount());
        dto.setChequeDebitTransactionsAmount(tx.getChequeDebitTransactionsAmount());
        dto.setChequeDebitTransactionsNumber(tx.getChequeDebitTransactionsNumber());
        dto.setCreatedAt(convertCalendarToEpochMillis(tx.getCreatedAt()));
        dto.setCreatedDate(convertCalendarToEpochMillis(tx.getCreatedDate()));
        dto.setCredittransactionsAmount(tx.getCredittransactionsAmount());
        dto.setDebitcardpostransactionsAmount(tx.getDebitcardpostransactionsAmount());
        dto.setDebitcardpostransactionsNumber(tx.getDebitcardpostransactionsNumber());
        dto.setFincominglocaltransactioncrAmount(tx.getFincominglocaltransactioncrAmount());
        dto.setId(tx.getId());
        dto.setIncominginternationaltrncrAmount(tx.getIncominginternationaltrncrAmount());
        dto.setIncominginternationaltrncrNumber(tx.getIncominginternationaltrncrNumber());
        dto.setIncominglocaltransactioncrNumber(tx.getIncominglocaltransactioncrNumber());
        dto.setIntrestAmount(tx.getIntrestAmount());
        dto.setLastTransactionDate(convertCalendarToEpochMillis(tx.getLastTransactionDate()));
        dto.setLastTransactionType(tx.getLastTransactionType());
        dto.setLastTransactionValue(tx.getLastTransactionValue());
        dto.setMaxAtmTransactions(tx.getMaxAtmTransactions());
        dto.setMaxMonthlyBebitTransactions(tx.getMaxMonthlyBebitTransactions());
        dto.setMaxalternativechanneltrnscr(tx.getMaxalternativechanneltrnscr());
        dto.setMaxalternativechanneltrnsdebit(tx.getMaxalternativechanneltrnsdebit());
        dto.setMaxbouncedchequetransactionscr(tx.getMaxbouncedchequetransactionscr());
        dto.setMaxchequedebittransactions(tx.getMaxchequedebittransactions());
        dto.setMaxdebitcardpostransactions(tx.getMaxdebitcardpostransactions());
        dto.setMaxincominginternationaltrncr(tx.getMaxincominginternationaltrncr());
        dto.setMaxincominglocaltransactioncr(tx.getMaxincominglocaltransactioncr());
        dto.setMaxmobilemoneycredittrn(tx.getMaxmobilemoneycredittrn());
        dto.setMaxmobilemoneydebittransaction(tx.getMaxmobilemoneydebittransaction());
        dto.setMaxmonthlycredittransactions(tx.getMaxmonthlycredittransactions());
        dto.setMaxoutgoinginttrndebit(tx.getMaxoutgoinginttrndebit());
        dto.setMaxoutgoinglocaltrndebit(tx.getMaxoutgoinglocaltrndebit());
        dto.setMaxoverthecounterwithdrawals(tx.getMaxoverthecounterwithdrawals());
        dto.setMinAtmTransactions(tx.getMinAtmTransactions());
        dto.setMinMonthlyDebitTransactions(tx.getMinMonthlyDebitTransactions());
        dto.setMinalternativechanneltrnscr(tx.getMinalternativechanneltrnscr());
        dto.setMinalternativechanneltrnsdebit(tx.getMinalternativechanneltrnsdebit());
        dto.setMinbouncedchequetransactionscr(tx.getMinbouncedchequetransactionscr());
        dto.setMinchequedebittransactions(tx.getMinchequedebittransactions());
        dto.setMindebitcardpostransactions(tx.getMindebitcardpostransactions());
        dto.setMinincominginternationaltrncr(tx.getMinincominginternationaltrncr());
        dto.setMinincominglocaltransactioncr(tx.getMinincominglocaltransactioncr());
        dto.setMinmobilemoneycredittrn(tx.getMinmobilemoneycredittrn());
        dto.setMinmobilemoneydebittransaction(tx.getMinmobilemoneydebittransaction());
        dto.setMinmonthlycredittransactions(tx.getMinmonthlycredittransactions());
        dto.setMinoutgoinginttrndebit(tx.getMinoutgoinginttrndebit());
        dto.setMinoutgoinglocaltrndebit(tx.getMinoutgoinglocaltrndebit());
        dto.setMinoverthecounterwithdrawals(tx.getMinoverthecounterwithdrawals());
        dto.setMobilemoneycredittransactionAmount(tx.getMobilemoneycredittransactionAmount());
        dto.setMobilemoneycredittransactionNumber(tx.getMobilemoneycredittransactionNumber());
        dto.setMobilemoneydebittransactionAmount(tx.getMobilemoneydebittransactionAmount());
        dto.setMobilemoneydebittransactionNumber(tx.getMobilemoneydebittransactionNumber());
        dto.setMonthlyBalance(tx.getMonthlyBalance());
        dto.setMonthlydebittransactionsAmount(tx.getMonthlydebittransactionsAmount());
        dto.setOutgoinginttransactiondebitAmount(tx.getOutgoinginttransactiondebitAmount());
        dto.setOutgoinginttrndebitNumber(tx.getOutgoinginttrndebitNumber());
        dto.setOutgoinglocaltransactiondebitAmount(tx.getOutgoinglocaltransactiondebitAmount());
        dto.setOutgoinglocaltransactiondebitNumber(tx.getOutgoinglocaltransactiondebitNumber());
        dto.setOverdraftLimit(tx.getOverdraftLimit());
        dto.setOverthecounterwithdrawalsAmount(tx.getOverthecounterwithdrawalsAmount());
        dto.setOverthecounterwithdrawalsNumber(tx.getOverthecounterwithdrawalsNumber());
        dto.setTransactionValue(tx.getTransactionValue());
        dto.setUpdatedAt(convertCalendarToEpochMillis(tx.getUpdatedAt()));

        return dto;
    }

    private long convertCalendarToEpochMillis(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return 0L;
        }
        return calendar.toGregorianCalendar()
                .toInstant()
                .toEpochMilli();
    }


}
