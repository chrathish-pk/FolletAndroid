/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */
package com.follett.fsc.mobile.circdesk.app.base;

import com.follett.fsc.mobile.circdesk.utils.FollettLog;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.ScannerNotClaimedException;
import com.honeywell.aidc.ScannerUnavailableException;
import com.honeywell.aidc.UnsupportedPropertyException;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.follett.fsc.mobile.circdesk.utils.AppConstants.BARCODE_READER_NOT_AVAILABLE;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.SCANNER_PROPERTY_FAILED;
import static com.follett.fsc.mobile.circdesk.utils.AppConstants.SCANNER_UNAVAILABLE;

public class ScannerViewModel extends BaseViewModel {
    
    private static final String TAG = ScannerViewModel.class.getSimpleName();
    
    public ScannerViewModel(@NonNull Application application) {
        super(application);
    }
    
    public BarcodeReader setPropertyForBarcodeReader(BarcodeReader barcodeReader) {
        
        if (barcodeReader != null) {
            
            try {
                barcodeReader.setProperty(BarcodeReader.PROPERTY_TRIGGER_CONTROL_MODE, BarcodeReader.TRIGGER_CONTROL_MODE_AUTO_CONTROL);
            } catch (UnsupportedPropertyException e) {
                setErrorMessage(SCANNER_PROPERTY_FAILED);
            }
            
            Map<String, Object> properties = new HashMap<>();
            properties.put(BarcodeReader.PROPERTY_CODE_128_ENABLED, true);
            properties.put(BarcodeReader.PROPERTY_GS1_128_ENABLED, true);
            properties.put(BarcodeReader.PROPERTY_QR_CODE_ENABLED, true);
            properties.put(BarcodeReader.PROPERTY_CODE_39_ENABLED, true);
            properties.put(BarcodeReader.PROPERTY_DATAMATRIX_ENABLED, true);
            properties.put(BarcodeReader.PROPERTY_UPC_A_ENABLE, true);
            properties.put(BarcodeReader.PROPERTY_EAN_13_ENABLED, false);
            properties.put(BarcodeReader.PROPERTY_AZTEC_ENABLED, false);
            properties.put(BarcodeReader.PROPERTY_CODABAR_ENABLED, false);
            properties.put(BarcodeReader.PROPERTY_INTERLEAVED_25_ENABLED, false);
            properties.put(BarcodeReader.PROPERTY_PDF_417_ENABLED, false);
            // Set Max Code 39 barcode length
            properties.put(BarcodeReader.PROPERTY_CODE_39_MAXIMUM_LENGTH, 10);
            // Turn on center decoding
            properties.put(BarcodeReader.PROPERTY_CENTER_DECODE, true);
            // Enable bad read response
            properties.put(BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED, true);
            // Apply the settings
            barcodeReader.setProperties(properties);
        }
        return barcodeReader;
    }
    
    public void triggerSoftwareScanner(BarcodeReader barcodeReader) {
        if (barcodeReader != null) {
            try {
                barcodeReader.softwareTrigger(true);
            } catch (ScannerNotClaimedException e) {
                FollettLog.e(TAG, e.getMessage());
                setErrorMessage(e.getMessage());
            } catch (ScannerUnavailableException e) {
                FollettLog.e(TAG, e.getMessage());
                setErrorMessage(e.getMessage());
            }
        } else {
            setErrorMessage(BARCODE_READER_NOT_AVAILABLE);
        }
    }
    
    public void onBarcodeFailureEvent(BarcodeReader barcodeReader) {
        if (barcodeReader == null) {
            return;
        }
        try {
            barcodeReader.softwareTrigger(false);
        } catch (ScannerNotClaimedException e) {
            FollettLog.e(TAG, e.getMessage());
        } catch (ScannerUnavailableException e) {
            FollettLog.e(TAG, e.getMessage());
        }
    }
    
    public void onResumeScanner(BarcodeReader barcodeReader) {
        if (barcodeReader != null) {
            try {
                barcodeReader.claim();
            } catch (ScannerUnavailableException e) {
                FollettLog.e(TAG, e.getMessage());
                setErrorMessage(SCANNER_UNAVAILABLE);
            }
        }
    }
    
    public void onPauseScanner(BarcodeReader barcodeReader) {
        if (barcodeReader != null) {
            barcodeReader.release();
        }
    }
    
    public void onDestoryScanner(BarcodeReader barcodeReader, AidcManager manager) {
        if (barcodeReader != null) {
            barcodeReader.close();
        }
        
        if (manager != null) {
            manager.close();
        }
    }
}
