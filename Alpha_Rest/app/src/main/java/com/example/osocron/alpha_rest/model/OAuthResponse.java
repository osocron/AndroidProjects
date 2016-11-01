package com.example.osocron.alpha_rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class OAuthResponse implements Parcelable{

    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("expires_in")
    @Expose
    public int expiresIn;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;

    public OAuthResponse(Parcel source) {
        this.tokenType = source.readString();
        this.accessToken = source.readString();
        this. expiresIn = source.readInt();
        this.refreshToken = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tokenType);
        dest.writeString(accessToken);
        dest.writeInt(expiresIn);
        dest.writeString(refreshToken);
    }

    public static final Parcelable.Creator<OAuthResponse> CREATOR = new Parcelable.Creator<OAuthResponse>() {

        @Override
        public OAuthResponse createFromParcel(Parcel source) {
            return new OAuthResponse(source);
        }

        @Override
        public OAuthResponse[] newArray(int size) {
            return new OAuthResponse[size];
        }
    };
}
