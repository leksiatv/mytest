class CheckedOffers {
    private String offerId;
    private Boolean isNewOffer = false;
    private Boolean isModOffer = false;
    private Boolean isRmOffer = false;
    private Boolean isBrokenImgOffer = false;

    void setId(String offerId) {
        this.offerId = offerId;
    }

    boolean getIsNewOffer() {
        return isNewOffer;
    }

    void setIsNewOffer(boolean isNewOffer) {
        this.isNewOffer = isNewOffer;
    }

    boolean getisModOffer() {
        return isModOffer;
    }

    void setIsModOffer(boolean isModOffer) {
        this.isModOffer = isModOffer;
    }

    boolean getIsRmOffer() {
        return isRmOffer;
    }

    void setIsRmOffer(boolean isRmOffer) {
        this.isRmOffer = isRmOffer;
    }

    boolean getIsBrokenImgOffer() {
        return isBrokenImgOffer;
    }

    void setIsBrokenImgOffer(boolean isBrokenImgOffer) {
        this.isBrokenImgOffer = isBrokenImgOffer;
    }

    @Override

    public String toString() {
        String params = "";
        if (this.isNewOffer) {
            params += "n";
        }
        if (this.isModOffer) {
            params += "m";
        }
        if (this.isRmOffer) {
            params += "r";
        }
        if (this.isBrokenImgOffer) {
            params += "p";
        }
        if (!params.equals("")) {
            return this.offerId + params;
        }
        return null;
    }
}
