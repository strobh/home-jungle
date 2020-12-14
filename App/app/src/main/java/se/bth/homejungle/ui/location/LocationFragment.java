package se.bth.homejungle.ui.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class LocationFragment extends Fragment {
    public enum LocationResult {
        SUCCESS, TIMEOUT, PROVIDER_DISABLED, PERMISSION_DENIED, MANAGER_NULL
    }

    private static final int REQUEST_LOCATION_PERMISSION = 10;
    private static final int REQUEST_SETTINGS_LOCATION_SERVICE = 11;
    private static final int REQUEST_SETTINGS_LOCATION_PERMISSION = 12;

    private static final int GPS_TIMEOUT_IN_MS = 5000;

    private LocationListener locationListener;

    /**
     * Whether the user is currently presented with the advanced settings dialog for the location permission.
     */
    private boolean settingsDialogIsShown = false;

    /**
     * Checks whether the location permission is granted and requests permission if not.
     */
    public boolean checkLocationPermission() {
        // If we are still waiting for an answer from the user, do not check
        if (settingsDialogIsShown) {
            return false;
        }

        if (!isLocationPermissionGranted()) {
            Log.v("LocationActivity::checkLocationPermission", "Location permission is not granted. Requesting permission.");
            requestLocationPermission();
            return false;
        } else {
            Log.v("LocationActivity::checkLocationPermission", "Location permission is granted.");
            return true;
        }
    }

    /**
     * Returns whether the location permission is granted or not.
     *
     * @return True if the location permission is granted, false otherwise.
     */
    public boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests the location permission.
     * {@link #onRequestPermissionsResult(int, String[], int[])} is called with the result.
     */
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_LOCATION_PERMISSION);
    }

    /**
     * This callback is called with the result after the location permission was requested.
     * Source of part of the code: https://stackoverflow.com/a/46751834
     *
     * @param requestCode  Request code: {@link #REQUEST_LOCATION_PERMISSION}
     * @param permissions  The requested permissions.
     * @param grantResults The grant results for the corresponding permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != REQUEST_LOCATION_PERMISSION || grantResults.length == 0) {
            return;
        }

        // Permission was granted
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("LocationActivity::result", "Location permission was granted.");
        }
        // Permission was denied and user clicked 'Never Ask Again', show a dialog
        else if (!shouldShowRequestPermissionRationale(permissions[0])) {
            Log.v("LocationActivity::result", "Location permission was denied and another attempt is not allowed. Show settings dialog.");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Project Densium needs your location");
            alertDialogBuilder
                    .setMessage("Project Densium needs your location in order to work. " +
                            "Click SETTINGS to manually grant the permission.")
                    .setCancelable(false)
                    .setPositiveButton("SETTINGS", (dialog, id) -> {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_SETTINGS_LOCATION_PERMISSION);
                        settingsDialogIsShown = false;
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            settingsDialogIsShown = true;
        }
        // Permission was denied
        else {
            Log.v("LocationActivity::result", "Location permission was denied.");
        }
    }


    /**
     * Returns whether the GPS location service is enabled or not.
     *
     * @return True if the GPS location service is enabled, false otherwise.
     */
    public boolean isLocationServiceEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) {
            return false;
        }
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * Checks whether the GPS location service is enabled and requests to enable it if not.
     *
     * @return True if the GPS location service is enabled, false otherwise.
     */
    public boolean checkLocationService() {
        if (!isLocationServiceEnabled()) {
            Log.v("LocationActivity::checkLocationService", "Location service is not enabled. Request to enable it.");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Enable GPS");
            alertDialogBuilder
                    .setMessage("Project Densium needs your GPS location in order to work. " +
                            "Click SETTINGS to manually enable GPS.")
                    .setCancelable(false)
                    .setPositiveButton("SETTINGS", (dialog, id) -> {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        //Uri uri = Uri.fromParts("package", getPackageName(), null);
                        //intent.setData(uri);
                        startActivityForResult(intent, REQUEST_SETTINGS_LOCATION_SERVICE);
                        settingsDialogIsShown = false;
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            settingsDialogIsShown = true;
            return false;
        } else {
            Log.v("LocationActivity::checkLocationService", "Location service is enabled.");
            return true;
        }
    }

    /**
     * Callback that is called whenever a settings dialog (i.e. in {@link #checkLocationService()}
     * and {@link #onRequestPermissionsResult(int, String[], int[])}) was closed.
     *
     * @param requestCode Request code of the dialog.
     * @param resultCode Result of the opened activity.
     * @param data Data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SETTINGS_LOCATION_PERMISSION && !isLocationPermissionGranted()) {
            // TODO: callback
            requestLocationPermission();
        } else if (requestCode == REQUEST_SETTINGS_LOCATION_SERVICE) {
            checkLocationService();
        }
    }


    /**
     * Needed for {@link #requestLocation(boolean, LocationCallback)}.
     */
    public interface LocationCallback {
        /**
         * Callback after requesting the location.
         *
         * @param locationResult
         *      Code representing the result:
         *          - {@link LocationResult#SUCCESS}
         *          - {@link LocationResult#TIMEOUT}
         *          - {@link LocationResult#PROVIDER_DISABLED}
         *          - {@link LocationResult#PERMISSION_DENIED}
         *          - {@link LocationResult#MANAGER_NULL}
         * @param location The location if successful, null otherwise
         */
        void onLocationResult(LocationResult locationResult, Location location);
    }

    /**
     * Requests the current location and calls the {@link LocationCallback} with the result.
     *
     * Comparison between LocationManager and LocationServices: https://stackoverflow.com/a/33023788
     *
     * @param callback Object implementing {@link LocationCallback} to be called back with the location.
     */
    @SuppressLint("MissingPermission")
    public void requestLocation(boolean requestSingleUpdate, LocationCallback callback) {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager == null) {
            Log.v("LocationActivity", "Location manager is null.");
            callback.onLocationResult(LocationResult.MANAGER_NULL, null);
            return;
        }

        if (!isLocationPermissionGranted()) {
            Log.v("LocationActivity", "Location permission is denied.");
            callback.onLocationResult(LocationResult.PERMISSION_DENIED, null);
            return;
        }

        // Handler used to wait for timeout for location update
        final Handler handler = new Handler();

        // Listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.v("LocationActivity::requestLocation", "Received location update.");
                handler.removeCallbacksAndMessages(null); // stop timeout
                if (requestSingleUpdate) {
                    locationManager.removeUpdates(this);
                }
                callback.onLocationResult(LocationResult.SUCCESS, location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.v("LocationActivity::requestLocation", "Location provider status changed.");
            }

            public void onProviderEnabled(String provider) {
                Log.v("LocationActivity::requestLocation", "Location provider enabled.");
            }

            public void onProviderDisabled(String provider) {
                Log.v("LocationActivity::requestLocation", "Location provider was disabled.");
                handler.removeCallbacksAndMessages(null); // stop timeout
                locationManager.removeUpdates(this);
                callback.onLocationResult(LocationResult.PROVIDER_DISABLED, null);
            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, locationListener);

        // Timeout for location update: https://stackoverflow.com/a/15890538
        handler.postDelayed(() -> {
            locationManager.removeUpdates(locationListener);
            callback.onLocationResult(LocationResult.TIMEOUT, null);
        }, GPS_TIMEOUT_IN_MS);
    }

    public void stopRequestingLocations() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null || locationListener == null) {
            Log.v("LocationActivity", "Location manager or location listener is null.");
            return;
        }

        locationManager.removeUpdates(locationListener);
    }
}
