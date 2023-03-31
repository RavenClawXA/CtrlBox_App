    package com.example.ctrlbox_app;

    import android.content.Intent;
    import android.widget.TextView;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class Oncrud {
        private RetrofitAPI retrofitAPI;

        private String BoxId;
        private String Vendor;
        private String Vendorname;
        private String Trandate;


        public Oncrud(String BoxId , String Vendor, String Vendorname, String Trandate) {
            this.BoxId =BoxId;
            this.Vendor = Vendor;
            this.Vendorname = Vendorname;
            this.Trandate = Trandate;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitAPI = retrofit.create(RetrofitAPI.class);
        }
        public static int extractBoxIdFromScannedData (String scannedData){
            String BoxId = scannedData.substring(3);
            return Integer.parseInt(BoxId);

        }

        public void fetchBoxData(final int BoxId) {
            Call<Datamodels> call = retrofitAPI.getDataById(BoxId);
            call.enqueue(new Callback<Datamodels>() {
                TextView boxIdTextView ;
                TextView vendorTextView ;
                TextView vendorNameTextView ;
                @Override
                public void onResponse(Call<Datamodels> call, Response<Datamodels> response) {
                    if (response.isSuccessful()) {
                        Datamodels datamodels = response.body();

                        boxIdTextView.setText(Integer.toString(Datamodels.getBoxId()));
                        vendorTextView.setText(datamodels.getVendor());
                        vendorNameTextView.setText(datamodels.getVendorName());

                    } else {
                        // handle error
                    }
                }

                @Override
                public void onFailure(Call<Datamodels> call, Throwable t) {
                    // handle error
                }
            });

        }
    }