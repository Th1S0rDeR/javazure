// MIT License
// Copyright (c) Microsoft Corporation. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE

package blobQuickstart.blobAzureApp;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;

import java.io.*;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class BlobReaderToStream
{
	/* *************************************************************************************************************************
	* Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
	* *************************************************************************************************************************
	*/
	public static final String storageConnectionString =
	"DefaultEndpointsProtocol=https;" +
	"AccountName=<MY_ACCOUNT_NAME_HERE>;" +
	"AccountKey=<MY_ACCOUNT_KEY_NAME_HERE>";


	private class Services{
        private String Account_Name;
        private String CaseSafeID;
        private String Key_Account_Contact_First_Name;
        private String Key_Account_Contact_Last_Name;
        private String Key_Account_Contact_Job_Title;
        private String Key_Contact_Email;
        private String Account_Owner_Full_Name;
        private String Practice_Area;
        private String Account_Owner_Department_Sub_Team;
        private String Service_Name;
        private String Practice_Area_2;
        private String Owner_Email;
        private String Data_Protection_Officer_Full_Name;
        private String Data_Protection_Officer_Email;
        private String Legal_Basis_for_Processing;
        private String Confirmed_No_DPO;
        private String DPO_TBC;
        private String Scheme_Actuary;



    }

	public static void main( String[] args ) throws IOException,
            GeneralSecurityException, StorageException, URISyntaxException
	{
	    int count = 0;

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;

        storageAccount = CloudStorageAccount.parse(storageConnectionString);
        blobClient = storageAccount.createCloudBlobClient();
        container = blobClient.getContainerReference("<NAME_OF_MY_CONTAINER>");

        CloudBlockBlob blobSource = container.getBlockBlobReference("<NAME_OF_MY_FILE>.csv");

        BlobInputStream inputStream = blobSource.openInputStream();
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            System.out.println("-----------"+count+"---------------");

            String[] values = scanner.next().split(",");
            for(int i=0;i< values.length;i++)
                System.out.println(values[i]);

            count++;
        }
	}

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
