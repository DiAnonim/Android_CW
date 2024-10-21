package kz.example.lesson_3.Lesson14.koinModule;

import android.widget.TextView;

public class AsyncTask extends android.os.AsyncTask<Void, Integer, String> {

    private TextView statusTextView;

    public AsyncTask(TextView statusTextView) {
        this.statusTextView = statusTextView;
    }

    @Override
    protected String doInBackground(Void... voids) {
        for(int i = 1; i <= 5; i++){
            try{
                Thread.sleep(1000);

            }catch (Exception e){
                e.printStackTrace();
            }
            publishProgress(i*20);
        }
        return "Task completed";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        statusTextView.setText("Starting task");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        statusTextView.setText("Progress: " + values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        statusTextView.setText("Result:" + s);
    }
}
