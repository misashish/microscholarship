package com.auro.scholr.home.data.model;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

public class AuroScholarInputModel implements Parcelable {

    String mobileNumber;
    Activity activity;
    int fragmentContainerUiId;
    String studentClass;
    String regitrationSource="";
    String referralLink="";
    String partnerSource;

    protected AuroScholarInputModel(Parcel in) {
        mobileNumber = in.readString();
        fragmentContainerUiId = in.readInt();
        studentClass = in.readString();
        regitrationSource = in.readString();
        referralLink = in.readString();
        partnerSource = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobileNumber);
        dest.writeInt(fragmentContainerUiId);
        dest.writeString(studentClass);
        dest.writeString(regitrationSource);
        dest.writeString(referralLink);
        dest.writeString(partnerSource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AuroScholarInputModel> CREATOR = new Creator<AuroScholarInputModel>() {
        @Override
        public AuroScholarInputModel createFromParcel(Parcel in) {
            return new AuroScholarInputModel(in);
        }

        @Override
        public AuroScholarInputModel[] newArray(int size) {
            return new AuroScholarInputModel[size];
        }
    };

    public String getPartnerSource() {
        return partnerSource;
    }

    public void setPartnerSource(String partnerSource) {
        this.partnerSource = partnerSource;
    }

    public AuroScholarInputModel() {
    }




    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getRegitrationSource() {
        return regitrationSource;
    }

    public void setRegitrationSource(String regitrationSource) {
        this.regitrationSource = regitrationSource;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getFragmentContainerUiId() {
        return fragmentContainerUiId;
    }

    public void setFragmentContainerUiId(int fragmentContainerUiId) {
        this.fragmentContainerUiId = fragmentContainerUiId;
    }


}
