package com.reagryan.online_banking.dto.response;


import java.util.Objects;

public class CardResponse {
    private boolean error;
    private String message;
    private Data data;

    public CardResponse(boolean error, String message, Data data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public CardResponse() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardResponse that = (CardResponse) o;
        return error == that.error && Objects.equals(message, that.message) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, message, data);
    }

    @Override
    public String toString() {
        return "BankCardResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
