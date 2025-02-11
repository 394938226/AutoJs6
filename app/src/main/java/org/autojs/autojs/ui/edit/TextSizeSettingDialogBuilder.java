package org.autojs.autojs.ui.edit;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;

import org.autojs.autojs6.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stardust on 2018/2/24.
 */
public class TextSizeSettingDialogBuilder extends MaterialDialog.Builder implements SeekBar.OnSeekBarChangeListener {

    public interface PositiveCallback {

        void onPositive(int value);
    }

    private final int mMinTextSize;
    private final int mMaxTextSize;

    @BindView(R.id.seekbar)
    SeekBar mSeekBar;

    @BindView(R.id.preview_text)
    TextView mPreviewText;

    private int mTextSize;
    private MaterialDialog mMaterialDialog;

    public TextSizeSettingDialogBuilder(@NonNull Context context) {
        super(context);

        mMinTextSize = Integer.parseInt(context.getString(R.string.text_text_size_min_value));
        mMaxTextSize = Integer.parseInt(context.getString(R.string.text_text_size_max_value));

        View view = View.inflate(context, R.layout.dialog_text_size_setting, null);
        customView(view, false);
        title(R.string.text_text_size);

        ButterKnife.bind(this, view);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setMax(mMaxTextSize - mMinTextSize);
        autoDismiss(false);

        neutralText(R.string.dialog_button_use_default);
        neutralColorRes(R.color.dialog_button_reset);
        onNeutral((dialog, which) -> initialValue(Integer.parseInt(getContext().getString(R.string.text_text_size_default_value))));
        negativeText(R.string.text_cancel);
        onNegative((dialog, which) -> dialog.dismiss());
        positiveText(R.string.text_ok);
        onPositive((dialog, which) -> dialog.dismiss());
    }

    private void setTextSize(int textSize) {
        mTextSize = textSize;
        String title = getContext().getString(R.string.text_text_size_current_value, textSize);
        if (mMaterialDialog != null) {
            mMaterialDialog.setTitle(title);
        } else {
            title(title);
        }
        mPreviewText.setTextSize(textSize);
    }

    public TextSizeSettingDialogBuilder initialValue(int value) {
        mSeekBar.setProgress(value - mMinTextSize);
        return this;
    }

    public TextSizeSettingDialogBuilder callback(PositiveCallback callback) {
        onPositive((dialog, which) -> {
            callback.onPositive(mTextSize);
            dialog.dismiss();
        });
        return this;
    }

    @Override
    public MaterialDialog build() {
        mMaterialDialog = super.build();
        return mMaterialDialog;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setTextSize(progress + mMinTextSize);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Ignored.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Ignored.
    }

}
