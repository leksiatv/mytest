class Check_image {
    static Boolean checkIMG(String imgList) throws Exception {
        String impsUrlList[] = imgList.split(";");
        for (String imgUrl : impsUrlList) {
            if (!exists(imgUrl)) {
                return false;

            }
        }

        return true;
    }

    private static boolean exists(String URLName) {
        try {
            java.net.HttpURLConnection.setFollowRedirects(false);
            java.net.HttpURLConnection con =
                    (java.net.HttpURLConnection) new java.net.URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == java.net.HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
