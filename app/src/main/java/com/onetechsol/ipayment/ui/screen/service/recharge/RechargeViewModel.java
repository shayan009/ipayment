package com.onetechsol.ipayment.ui.screen.service.recharge;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onetechsol.ipayment.pojo.Bill;
import com.onetechsol.ipayment.pojo.ElectricityBillPayDto;
import com.onetechsol.ipayment.pojo.FetchBillDetails;
import com.onetechsol.ipayment.pojo.FetchBillRequest;
import com.onetechsol.ipayment.pojo.GetOperatorInfoRequest;
import com.onetechsol.ipayment.pojo.GetOperatorRequest;
import com.onetechsol.ipayment.pojo.OpCircleItemDto;
import com.onetechsol.ipayment.pojo.OperatorCircleItemModel;
import com.onetechsol.ipayment.pojo.OperatorInfoItem;
import com.onetechsol.ipayment.pojo.OperatorItem;
import com.onetechsol.ipayment.ui.basefiles.BaseViewModel;
import com.onetechsol.ipayment.utils.ApiConstant;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RechargeViewModel extends BaseViewModel {


    public Observable<OperatorCircleItemModel> getOperator(String serviceId) {

        GetOperatorRequest getOperatorRequest = new GetOperatorRequest(serviceId);

        return iModelRepository().getOperator(getOperatorRequest).map(m -> {
            List<OpCircleItemDto> opCircleItemDtos = new ArrayList<>();

            List<OperatorItem> operatorItems = m.data().operatorItemList();

            for (int i = 0; i < operatorItems.size(); i++) {
                OperatorItem operatorItem = operatorItems.get(i);
                opCircleItemDtos.add(new OpCircleItemDto(operatorItem.id(), operatorItem.label(), ApiConstant.BASE_URL_IMAGE_SERVICE + operatorItem.img().replaceAll("\\/", "/"), 1));
            }

            opCircleItemDtos.removeIf(p -> p.id().equalsIgnoreCase("0"));

            return new OperatorCircleItemModel(true, "Select Operator", "Search Operator", "Enter your text", opCircleItemDtos);

        }).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<ElectricityBillPayDto> getOperatorInfo(String operator, String serviceCode, String title, ElectricityBillPayDto electricityBillPayDto) {

        GetOperatorInfoRequest getOperatorCircleRequest = new GetOperatorInfoRequest(operator + "|" + title, serviceCode);

        return iModelRepository().getOperatorInfo(getOperatorCircleRequest).map(m -> {

            List<OperatorInfoItem> operatorInfoItems = m.data().operatorInfoItemList();

            operatorInfoItems.forEach(operatorInfoItem -> {

                String name = operatorInfoItem.name();
                String view = operatorInfoItem.view();
                String label = operatorInfoItem.label();


                switch (name) {
                    case "txn_number":
                        electricityBillPayDto.setTxnNumber(label);
                        electricityBillPayDto.setTxnNumberShow(view.equals("1"));
                        break;
                    case "txn_number_name":
                        electricityBillPayDto.setTxnNumberName(label);
                        break;
                    case "txn_number_regex":
                        electricityBillPayDto.setTxnNumberRegex(label);
                        break;
                    case "txn_ad1":
                        electricityBillPayDto.setTxnAd1(label);
                        electricityBillPayDto.setTxnAd1Show(view.equals("1"));
                        break;
                    case "txn_ad1_name":
                        electricityBillPayDto.setTxnAd1Name(label);
                        break;
                    case "txn_ad1_regex":
                        electricityBillPayDto.setTxnAd1Regex(label);
                        break;
                    case "txn_ad2":
                        electricityBillPayDto.setTxnAd2(label);
                        electricityBillPayDto.setTxnAd2Show(view.equals("1"));
                        break;

                    case "txn_ad2_name":

                        electricityBillPayDto.setTxnAd2Name(label);
                        break;

                    case "txn_ad2_regex":

                        electricityBillPayDto.setTxnAd2Regex(label);
                        break;


                    case "txn_ad3":


                        electricityBillPayDto.setTxnAd3(label);
                        electricityBillPayDto.setTxnAd3Show(view.equals("1"));

                        break;

                    case "txn_ad3_name":

                        electricityBillPayDto.setTxnAd3Name(label);
                        break;

                    case "txn_ad3_regex":

                        electricityBillPayDto.setTxnAd3Regex(label);
                        break;

                    case "txn_ad4":

                        electricityBillPayDto.setTxnAd4(label);
                        electricityBillPayDto.setTxnAd4Show(view.equals("1"));

                        break;

                    default:
                }

            });
            return electricityBillPayDto;
        }).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FetchBillDetails> fetchBill(String serviceType, OpCircleItemDto selectedOperator, String txnNumber, String txnNumberName, String txnNumberRegex, String txnCustomerNo, String txnAd1, String txnAd1Name, String txnAd1Regex, String txnAd2, String txnAd2Name, String txnAd2Regex, String txnAd3, String txnAd3Name, String txnAd3Regex, String txnAd4) {

        FetchBillRequest fetchBillRequest = new FetchBillRequest(serviceType, selectedOperator.id() + "|" + selectedOperator.title(), txnNumber, txnNumberName, txnNumberRegex, txnCustomerNo, txnAd1, txnAd1Name, txnAd1Regex, txnAd2, txnAd2Name, txnAd2Regex, txnAd3, txnAd3Name, txnAd3Regex, txnAd4);

        return iModelRepository().fetchBill(fetchBillRequest).map(m -> {


            FetchBillDetails fetchBillDetails = new FetchBillDetails();

            if (m.status().equals("1")) {

                JsonObject parse = JsonParser.parseString(m.txnFetch()).getAsJsonObject();
                JsonObject data = parse.get("data").getAsJsonObject();
                JsonObject fetchReq = parse.get("fetch_req").getAsJsonObject();
                String customerLabel = fetchReq.get("tags").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                String customerID = fetchReq.get("tags").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();
                String billId = data.get("billerResponse").getAsJsonObject().get("billId").getAsString();
                String amount = data.get("billerResponse").getAsJsonObject().get("amount").getAsString();
                boolean amountOption = data.get("billerResponse").getAsJsonObject().get("amountOption").getAsBoolean();
                String billPeriod = data.get("billerResponse").getAsJsonObject().get("billPeriod").getAsString();
                String dueDate = data.get("billerResponse").getAsJsonObject().get("dueDate").getAsString();
                String customerName = m.customerName();
                String billNumber = m.billNumber();

                Bill bill = new Bill(billNumber, billId, dueDate, StringUtils.isNotEmpty(amount) ? amount : "0", billPeriod);

                List<Bill> billList = new ArrayList<>();
                billList.add(bill);

                if (amountOption) {

                    JsonArray tagList = data.get("billerResponse").getAsJsonObject().get("tagList").getAsJsonArray();
                    JsonArray additionalInfo = data.get("additionalInfo").getAsJsonArray();

                    Log.d("tagList", tagList.toString());
                    Log.d("additionalInfo", additionalInfo.toString());
                    for (int j = 0; j < tagList.size() - 1; j++) {
                        JsonObject asJsonObject = tagList.get(j).getAsJsonObject();
                        String name = asJsonObject.get("name").getAsString();
                        String billAmount = asJsonObject.get("value").getAsString();
                        String billDueDate = "";
                        String billPeriod2 = "";

                        for (int k = 0; k < additionalInfo.size(); k++) {
                            JsonObject asJsonObject1 = additionalInfo.get(k).getAsJsonObject();
                            String name1 = asJsonObject1.get("name").getAsString();
                            String val = asJsonObject1.get("value").getAsString();
                            if (name1.contains((j + 1) + " Month Period")) {
                                billPeriod2 = val;
                            } else if (name1.contains((k + 1) + " Month Due Date")) {
                                billDueDate = val;
                            }
                        }
                        Bill bill2 = new Bill(billNumber, billId, billDueDate, StringUtils.isNotEmpty(billAmount) ? billAmount : "0", billPeriod2);
                        billList.add(bill2);
                    }

                }


                fetchBillDetails.setTxnNumber(txnNumber);
                fetchBillDetails.setTxnCustomerNo(txnCustomerNo);
                fetchBillDetails.setTxnAd1(txnAd1);
                fetchBillDetails.setTxnAd2(txnAd2);
                fetchBillDetails.setTxnAd3(txnAd3);
                fetchBillDetails.setTxnAd4(txnAd4);
                fetchBillDetails.setCustomerLabel(customerLabel);
                fetchBillDetails.setCustomerId(customerID);
                fetchBillDetails.setOpCircleItemDto(selectedOperator);
                //fetchBillDetails.setServiceCategoryModel(serviceCategoryModel);
                fetchBillDetails.setCustomerName(customerName);
                //fetchBillDetails.setElectricityBillPayDto(electricityBillPayDto);
                fetchBillDetails.setTxnFetch(m.txnFetch());
                fetchBillDetails.setBillList(billList);
            }

            fetchBillDetails.setStatus(m.status());
            fetchBillDetails.setMessage(m.message());

            return fetchBillDetails;
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
