package net.kehui.www.t_907_origin.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.percentlayout.widget.PercentRelativeLayout;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnViewClickListener;

import net.kehui.www.t_907_origin.ConnectService;
import net.kehui.www.t_907_origin.R;
import net.kehui.www.t_907_origin.adpter.MyChartAdapterBase;
import net.kehui.www.t_907_origin.application.AppConfig;
import net.kehui.www.t_907_origin.application.Constant;
import net.kehui.www.t_907_origin.base.BaseActivity;
import net.kehui.www.t_907_origin.entity.ParamInfo;
import net.kehui.www.t_907_origin.ui.MoveView;
import net.kehui.www.t_907_origin.ui.MoveWaveView;
import net.kehui.www.t_907_origin.ui.SaveRecordsDialog;
import net.kehui.www.t_907_origin.ui.ShowRecordsDialog;
import net.kehui.www.t_907_origin.ui.HelpModeDialog;
import net.kehui.www.t_907_origin.ui.SparkView.SparkView;
import net.kehui.www.t_907_origin.util.StateUtils;
import net.kehui.www.t_907_origin.util.UnitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static net.kehui.www.t_907_origin.application.Constant.DISPLAY_ACTION;
import static net.kehui.www.t_907_origin.application.Constant.MI_UNIT;
import static net.kehui.www.t_907_origin.application.Constant.FT_UNIT;
import static net.kehui.www.t_907_origin.application.Constant.batteryValue;
import static net.kehui.www.t_907_origin.application.Constant.hasSavedPulseWidth;
import static net.kehui.www.t_907_origin.application.Constant.waveLen;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DEVICE_CONNECTED;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DEVICE_CONNECT_FAILURE;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DOWIFI_COMMAND;
import static net.kehui.www.t_907_origin.ConnectService.BROADCAST_ACTION_DOWIFI_WAVE;
import static net.kehui.www.t_907_origin.ConnectService.INTENT_KEY_COMMAND;
import static net.kehui.www.t_907_origin.ConnectService.INTENT_KEY_WAVE;

public class ModeActivity extends BaseActivity {

    @BindView(R.id.tv_gain_add)
    ImageView tvGainAdd;
    @BindView(R.id.tv_gain_min)
    ImageView tvGainMin;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    /**
     * ??????????????? //GT20200619
     */
    /*@BindView(R.id.tv_height)
    TextView tvHeight;*/
    /**
     * ?????????????????? //GC20190708
     */
    @BindView(R.id.tv_information)
    TextView tvInformation;
    @BindView(R.id.tv_pulse_width)
    ImageView tvPulseWidth;
    @BindView(R.id.tv_compare)
    ImageView tvCompare;
    @BindView(R.id.tv_cal)
    ImageView tvCal;
    @BindView(R.id.tv_range)
    ImageView tvRange;
    @BindView(R.id.tv_file)
    ImageView tvFile;
    @BindView(R.id.ll_adjust)
    LinearLayout llAdjust;
    @BindView(R.id.tv_home)
    ImageView tvHome;
    @BindView(R.id.tv_zero)
    ImageView tvZero;
    @BindView(R.id.tv_cursor_plus)
    ImageView tvCursorPlus;
    @BindView(R.id.tv_cursor_min)
    ImageView tvCursorMin;
    @BindView(R.id.tv_zoom_plus)
    ImageView tvZoomPlus;
    @BindView(R.id.tv_zoom_min)
    ImageView tvZoomMin;
    @BindView(R.id.tv_test)
    ImageView tvTest;
    @BindView(R.id.tv_help)
    ImageView tvHelp;
    @BindView(R.id.rl_feature)
    LinearLayout rlFeature;
    @BindView(R.id.mainWave)
    SparkView mainWave;
    @BindView(R.id.tv_mode)
    TextView tvMode;
    @BindView(R.id.tv_range_value)
    TextView tvRangeValue;
    @BindView(R.id.tv_gain_value)
    TextView tvGainValue;
    @BindView(R.id.tv_vop_value)
    TextView tvVopValue;
    @BindView(R.id.tv_balance_value)
    TextView tvBalanceValue;
    @BindView(R.id.tv_zoom_value)
    TextView tvZoomValue;
    @BindView(R.id.ll_info)
    PercentRelativeLayout llInfo;
    @BindView(R.id.tv_balance_plus)
    ImageView tvBalancePlus;
    @BindView(R.id.tv_balance_min)
    ImageView tvBalanceMin;
    @BindView(R.id.layout_tv_memory)
    ImageView layoutTvMemory;
    @BindView(R.id.layout_tv_both)
    ImageView layoutTvBoth;
    @BindView(R.id.ll_compare)
    LinearLayout llCompare;
    @BindView(R.id.tv_vop_plus)
    ImageView tvVopPlus;
    @BindView(R.id.tv_vop_min)
    ImageView tvVopMin;
    @BindView(R.id.tv_vop_save)
    ImageView tvSave;
    @BindView(R.id.ll_cal)
    LinearLayout llCal;
    @BindView(R.id.tv_250m)
    TextView tv250m;
    @BindView(R.id.tv_500m)
    TextView tv500m;
    @BindView(R.id.tv_1km)
    TextView tv1km;
    @BindView(R.id.tv_2km)
    TextView tv2km;
    @BindView(R.id.tv_4km)
    TextView tv4km;
    @BindView(R.id.tv_8km)
    TextView tv8km;
    @BindView(R.id.tv_16km)
    TextView tv16km;
    @BindView(R.id.tv_32km)
    TextView tv32km;
    @BindView(R.id.tv_64km)
    TextView tv64km;
    @BindView(R.id.ll_range)
    LinearLayout llRange;
    @BindView(R.id.tv_file_records)
    ImageView tvFileRecords;
    @BindView(R.id.ll_records)
    LinearLayout llRecords;
    @BindView(R.id.iv_compare_close)
    ImageView ivCompareClose;
    @BindView(R.id.iv_cal_close)
    ImageView ivGainClose;
    @BindView(R.id.iv_range_close)
    ImageView ivRangeClose;
    @BindView(R.id.iv_records_close)
    ImageView ivRecordsClose;
    @BindView(R.id.tv_decay_value)
    TextView tvDelayValue;
    @BindView(R.id.tv_delay_text)
    TextView tvDelayText;
    @BindView(R.id.tv_origin)
    ImageView tvOrigin;
    @BindView(R.id.tv_trigger_delay)
    ImageView tvTriggerDelay;
    @BindView(R.id.tv_records_save)
    ImageView tvRecordsSave;
    @BindView(R.id.tv_delay_plus)
    ImageView tvDelayPlus;
    @BindView(R.id.tv_delay_min)
    ImageView tvDelayMin;
    @BindView(R.id.ll_trigger_delay)
    LinearLayout llTriggerDelay;
    /**
     * ???????????????????????????
     */
    @BindView(R.id.ll_pulse_width)
    LinearLayout llPulseWidth;
    @BindView(R.id.iv_pulse_width_close)
    ImageView ivPulseWidthClose;
    @BindView(R.id.et_pulse_width_id)
    EditText etPulseWidth;
    @BindView(R.id.iv_wifi_status)
    ImageView ivWifiStatus;
    @BindView(R.id.iv_battery_status)
    ImageView ivBatteryStatus;
    @BindView(R.id.tv_balance_text)
    TextView tvBalanceText;
    @BindView(R.id.tv_balance_space)
    TextView tvBalanceSpace;
    @BindView(R.id.iv_close_trigger_delay)
    ImageView ivCloseTriggerDelay;
    @BindView(R.id.tv_delay_space)
    TextView tvDelaySpace;
    @BindView(R.id.tv_wave_text)
    TextView tvWaveText;
    @BindView(R.id.tv_wave_value)
    TextView tvWaveValue;
    @BindView(R.id.tv_wave_space)
    TextView tvWaveSpace;
    @BindView(R.id.view_move_vertical_wave)
    MoveWaveView viewMoveVerticalWave;
    @BindView(R.id.tv_wave_pre)
    ImageView tvWavePre;
    @BindView(R.id.tv_wave_next)
    ImageView tvWaveNext;
    @BindView(R.id.tv_distance_unit)
    TextView tvDistanceUnit;
    @BindView(R.id.rl_wave)
    RelativeLayout rlWave;
    @BindView(R.id.tv_vop_unit)
    TextView tvVopUnit;
    @BindView(R.id.mv_wave)
    MoveView mvWave;
    @BindView(R.id.tv_cable_vop_unit)
    TextView tvCableVopUnit;
    @BindView(R.id.tv_pulse_width_save)
    ImageView tvPulseWidthSave;
    @BindView(R.id.ll_horizontal_view)
    LinearLayout llHorizontalView;

    private int index;
    //????????????????????????
    private int fenzi1;
    //?????????????????????
    private int fenzi2;
    private int currentActionDownX = 0;

    private boolean alreadyDisplayWave;
    //20200407
    private boolean allowSetRange = true;
    //20200523
    private boolean canClickCancelButton;
    //???????????????????????????????????????????????????????????????????????????????????????????????????????????????
    private boolean isReceiveData = true;

    private TDialog tDialog;

    /**
     * ??????bundle???key-value
     */
    public static final String BUNDLE_MODE_KEY = "mode";
    public static final String BUNDLE_COMMAND_KEY = "command";
    public static final String BUNDLE_DATA_TRANSFER_KEY = "dataTransfer";
    public static final String BUNDLE_PARAM_KEY = "bundle_param_key";

    /**
     * ?????????handler??????????????????UI??????
     */
    public static final int DO_WAVE = 1;
    public static final int VIEW_REFRESH = 2;
    public static final int DISPLAY_DATABASE = 3;

    public Handler handler = new Handler(msg -> {
        switch (msg.what) {
            case DO_WAVE:
                //??????????????????
                int[] wifiStreamNew = msg.getData().getIntArray("WAVE");
                assert wifiStreamNew != null;
                doWifiWave(wifiStreamNew);
                break;
            case VIEW_REFRESH:
                if (mode != SIM) {
                    //??????????????????    //SIM???????????????
                    if (density < densityMax) {
                        //????????????
                        organizeZoomWaveData(currentStart);
                    } else {
                        organizeWaveData();
                    }
                    //????????????
                    displayWave();
                }
                if (mode == TDR) {    //jk20200807
                    //?????????????????????????????????????????????????????????????????????   //GC20200916
                    if (isLongClick) {
                        if (!longTestInit) {
                            longTestInit();
                        } else {
//                            tdrAutoTestLong();
                            if (!balanceIsReady) {
                                selectBalance();
                            } else {
                                if (!rangeIsReady) {
                                    selectRange();
                                } else {
                                    selectGain();
                                }
                            }
                        }
                    }
                }
                //TODO 20200407 ?????????????????????????????????????????????????????????????????????
                Constant.isTesting = false;
                ConnectService.canAskPower = true;
                allowSetRange = true;
                tvTest.setEnabled(true);
                Log.e("??????????????????????????????", "??????????????????????????????????????????");
                break;
            case DISPLAY_DATABASE:
                //??????????????????????????????????????? //GT20200629
               /* if ((mode == ICM) ){
                    icmAutoTest();
                } else if((mode == ICM_DECAY)){
                    icmAutoTestDC();
                } */ //jk20201023
                //??????????????????
                setDateBaseParameter();
                try {
                    organizeWaveData();
                    displayWave();
                } catch (Exception l_ex) {
                }
                break;
            default:
                break;
        }
        return false;
    });

    /**
     * ??????????????????
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            handler.sendEmptyMessage(intent.getIntExtra("display_action", 0));

            String action = intent.getAction();
            assert action != null;
            switch (action) {
                case BROADCAST_ACTION_DEVICE_CONNECTED:
                    //?????????toast???????????????????????????     //GC20211214
                    Toast.makeText(ModeActivity.this, R.string.connect_success, Toast.LENGTH_SHORT).show();
                    Log.e("ModeActivity", "????????????");
                    //?????????????????????????????????
                    ConnectService.isConnected = true;
                    ivWifiStatus.setImageResource(R.drawable.ic_wifi_connected);
                    //??????????????????????????????    //GC20200319
                    if (tDialog != null) {
                        tDialog.dismiss();
                        Log.e("DIA", "????????????????????????");
                    }
                    //??????????????????????????????????????????????????????????????????????????????????????????????????????
                    if (!isReceiveData || isDatabase) {
                        setModeNoCmd(Constant.Para[0]);
                        setRangeNoCmd(Constant.Para[1]);
                        setGain(Constant.Para[2]);
                        setVelocityNoCmd(Constant.Para[3]);
                        //?????????????????????????????????   //20200523
                        Constant.isTesting = false;
                        allowSetRange = true;
                        alreadyDisplayWave = false;
                    } else {
                        //?????????????????????
                        //??????????????????????????????true    //20200407
                        Constant.isTesting = false;
                        allowSetRange = true;
                        alreadyDisplayWave = false;
                        //???????????????????????????????????????
                        //??????
                        setMode(mode);
                        handler.postDelayed(() -> {
                            //??????
                            setRange(range);
                        }, 20);
                        handler.postDelayed(() -> {
                            //??????
                            setGain(gain);
                        }, 20);
                        //??????????????????????????????????????????  //GC20200424
                        if (mode == TDR) {
                            handler.postDelayed(() -> {
                                //??????
                                setPulseWidth(pulseWidth);
                            }, 20);
                        } else if (mode == ICM || mode == SIM) {
                            handler.postDelayed(() -> {
                                //??????
                                delay = 0;
                                setDelay(delay);
                            }, 20);
                            if (mode == SIM) {
                                handler.postDelayed(() -> {
                                    //??????
                                    setPulseWidth(pulseWidthSim);
                                }, 20);
                            }
                        }
                        //??????100???????????????????????????100????????????????????????????????????????????????????????????????????????????????????????????????
                        handler.postDelayed(ModeActivity.this::clickTest, 100);
                    }
                    break;
                case BROADCAST_ACTION_DEVICE_CONNECT_FAILURE:
                    //????????????????????????????????????????????????
                    ConnectService.isConnected = false;
                    ivWifiStatus.setImageResource(R.drawable.ic_no_wifi_connect);
                    ivBatteryStatus.setImageResource(R.drawable.ic_battery_no);
                    //????????????????????????   //GC20200314
                    Constant.batteryValue = -1;
                    break;
                case BROADCAST_ACTION_DOWIFI_COMMAND:
                    //??????????????????????????????
                    wifiStream = intent.getIntArrayExtra(INTENT_KEY_COMMAND);
                    assert wifiStream != null;
                    doWifiCommand(wifiStream);
                    break;
                case BROADCAST_ACTION_DOWIFI_WAVE:
                    //64??????????????????????????????????????????????????????????????????????????????
                    //int[] wifiStreamNew = ConnectService.mExtra;
                    //wifiStream = ConnectService.mExtra;
                    //assert wifiStreamNew != null;
                    int[] wifiStreamNew = intent.getIntArrayExtra(INTENT_KEY_WAVE);
                    if (wifiStreamNew[3] == WAVE_TDR_ICM_DECAY || wifiStreamNew[3] == WAVE_SIM) {
                        setWaveParameter();
                    }
                    Message message = Message.obtain();
                    message.what = ModeActivity.DO_WAVE;
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("WAVE", wifiStreamNew);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        Log.e("??????????????????", "??????mode?????????");
        ButterKnife.bind(this);

        alreadyDisplayWave = false;
        mode = getIntent().getIntExtra(BUNDLE_MODE_KEY, 0);
        isReceiveData = getIntent().getBooleanExtra("isReceiveData", true);
        initUnit();
        initSparkView();
        initViewMoveWave();
        initBtnRangeView();
        //??????????????????
        initPulseWidth();
        initRange();
        initFrame();
        setChartListener();
        initMode();
        initBroadcastReceiver();
        //?????????????????????????????????????????????????????????
        if (getIntent().getIntExtra("display_action", 0) == DISPLAY_DATABASE) {
            isReceiveData = false;
            Intent intent = new Intent(DISPLAY_ACTION);
            intent.putExtra("display_action", ModeActivity.DISPLAY_DATABASE);
            sendBroadcast(intent);
        }

    }

    /**
     * ????????????????????? (m ft)
     */
    private void initUnit() {
        Constant.CurrentUnit = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_SAVE_UNIT, MI_UNIT);
        Constant.CurrentSaveUnit = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_SAVE_UNIT, MI_UNIT);
        if (Constant.CurrentUnit == MI_UNIT) {
            tvDistanceUnit.setText(R.string.mi);
            tvVopUnit.setText(R.string.mius);
        } else {
            tvDistanceUnit.setText(R.string.ft);
            tvVopUnit.setText(R.string.ftus);
        }
    }

    /**
     * ?????????sparkView
     */
    public void initSparkView() {
        for (int i = 0; i < 510; i++) {
            waveArray[i] = 128;
            //Constant.WaveData[i] = 128;
        }
        myChartAdapterMainWave = new MyChartAdapterBase(waveArray, null,
                false, 0, false);
        mainWave.setAdapter(myChartAdapterMainWave);
        setMoveView();
        Log.i("Draw", "?????????????????????");
    }

    /**
     * ?????????????????????????????????
     */
    private void initViewMoveWave() {

        viewMoveVerticalWave.setViewMoveWaveListener(new MoveWaveView.ViewMoveWaveListener() {
            @Override
            public void onMoved(float x, float y) {
                //??????????????????
                mainWave.setSparkViewMove(y);
                Log.e("ModeActivity", y + "");
            }
            @Override
            public void onMoveEnded() {
            }
        });
        fenzi1 = (dataLength / (densityMax / density)) - 510;

        //??????????????????????????????????????????
        mvWave.setViewMoveWaveListener(x -> {
            //????????????????????????0?????????
            int currentMoverX = get510Value(x, mvWave.getParentWidth());
            Log.e("????????????", "??????X???" + x + " /???????????????" + mvWave.getParentWidth() + "????????????" + currentMoverX);
            //??????????????????????????????????????????
            currentStart = currentMoverX * densityMax;
            //????????????????????????
            organizeZoomWaveData(currentStart);
            try {
                organizeCompareWaveData(currentStart);
            } catch (Exception ignored) {
            }
            displayWave();
            //???????????????????????????????????????     //GC20200611   ????????????
            if ( (zero < currentStart) || (zero >= currentStart + 510 * density) ){
                mainWave.setScrubLineRealDisappear();
            } else {
                positionReal = (zero - currentStart) / density;
                mainWave.setScrubLineReal(positionReal);
            }
            //????????????????????????????????????????????????????????????????????????????????????
            positionVirtual = (pointDistance - currentStart) / density;
            if ( (pointDistance < currentStart) || (pointDistance >= currentStart + 510 * density) ){
                mainWave.setScrubLineVirtualDisappear();
            } else {
                mainWave.setScrubLineVirtual(positionVirtual);
            }
            //??????????????????????????????    //GC20200330
            if (mode ==SIM) {
                if ( (simStandardZero < currentStart) || (simStandardZero >= currentStart + 510 * density) ){
                    mainWave.setScrubLineSimDisappear();
                } else {
                    positionSim = simStandardZero / density;
                    mainWave.setScrubLineSim(positionSim);
                }
            }
            //?????????????????????510??????????????????
            currentMoverPosition510 = currentMoverX;
            Log.e("????????????", "?????????????????????: " + currentStart + " /??????????????????510????????????: " + currentMoverPosition510);
        });

    }

    /**
     * ???????????????????????????????????????????????????????????????510???????????????0?????????
     *
     * @param x ????????????????????????????????????????????????????????????
     * @param length ????????????
     * @return ???????????????
     */
    private int get510Value(float x, float length) {
        return (int) (510 * x / length);
    }

    /**
     * ???sparkView??????????????????
     */
    private void displayWave() {
        if (densityMax == 1) {
            //?????????????????????1???????????????????????????     //20200523
            tvZoomPlus.setEnabled(false);
            tvZoomMin.setEnabled(false);
        } else {
            tvZoomPlus.setEnabled(true);
        }
        if (mode == SIM) {
            if (isDatabase) {
                //?????????SIM????????????????????????  //GC20200604
                tvWavePre.setEnabled(false);
                tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_false);
                tvWaveNext.setEnabled(false);
                tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_false);
                //?????????SIM?????????????????????
                tvWaveText.setVisibility(View.GONE);
                tvWaveValue.setVisibility(View.GONE);
            } else {
                tvWaveText.setVisibility(View.VISIBLE);
                tvWaveValue.setVisibility(View.VISIBLE);
            }
        }
        //?????????
        myChartAdapterMainWave.setmTempArray(waveDraw);
        myChartAdapterMainWave.setShowCompareLine(isCom);
        if (mode == SIM) {
            if (isCom) {
                myChartAdapterMainWave.setmCompareArray(waveCompare);
            }
        }
        myChartAdapterMainWave.notifyDataSetChanged();
        //????????????????????????
        if (tDialog != null) {
            tDialog.dismissAllowingStateLoss();
            Log.e("DIA", "????????????????????????" + " ??????????????????");
        }
        alreadyDisplayWave = true;

    }

    /**
     * ??????????????????????????????????????????
     */
    private void initBtnRangeView() {
        if (Constant.CurrentUnit == FT_UNIT) {
            tv250m.setText(getResources().getString(R.string.btn_250m_to_ft));
            tv500m.setText(getResources().getString(R.string.btn_500m_to_ft));
            tv1km.setText(getResources().getString(R.string.btn_1km_to_yingli));
            tv2km.setText(getResources().getString(R.string.btn_2km_to_yingli));
            tv4km.setText(getResources().getString(R.string.btn_4km_to_yingli));
            tv8km.setText(getResources().getString(R.string.btn_8km_to_yingli));
            tv16km.setText(getResources().getString(R.string.btn_16km_to_yingli));
            tv32km.setText(getResources().getString(R.string.btn_32km_to_yingli));
            tv64km.setText(getResources().getString(R.string.btn_64km_to_yingli));
        }
    }

    /**
     * ??????????????????
     */
    private void initPulseWidth() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PULSE_WIDTH_INFO_KEY);
        if (paramInfo != null) {
            if (hasSavedPulseWidth) {
                //????????????????????????????????????????????????   //GC20200331
                pulseWidth = paramInfo.getPulseWidth();
                etPulseWidth.setText(String.valueOf(pulseWidth));
            }
        }

        etPulseWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int pulseWidth = Integer.parseInt(s.toString());
                    int maxPulseWidth = 7000;
                    if (pulseWidth > maxPulseWidth) {
                        Toast.makeText(ModeActivity.this, getResources().getString(R.string
                                .maxpulsewidth), Toast.LENGTH_SHORT).show();
                        etPulseWidth.setText(maxPulseWidth + "");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * ???????????????????????????????????????????????????????????????
     */
    private void initRange() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        double localRange;
        if (paramInfo != null && paramInfo.getCableLength() != null && !TextUtils.isEmpty(paramInfo.getCableLength())) {
            localRange = Double.valueOf(paramInfo.getCableLength());

            if (localRange == 0.0 || localRange == 0) {
                range = (0x11);
                rangeState = 1;
                gain = 13;
                if (Constant.CurrentUnit == FT_UNIT) {
                     tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                }
                //????????????????????????????????????????????? //GC20200331
                if (!hasSavedPulseWidth) {
                    pulseWidth = 40;
                    etPulseWidth.setText(String.valueOf(40));
                }
                //?????????SIM????????????    //GC20200527
                pulseWidthSim = 320;
            } else if (localRange <= 250) {
                range = (0x99);
                rangeState = 0;
                gain = 13;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 40;
                    etPulseWidth.setText(String.valueOf(40));
                }
                pulseWidthSim = 320;
            } else if (localRange > 250 && localRange <= 500) {
                range = (0x11);
                rangeState = 1;
                gain = 13;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 40;
                    etPulseWidth.setText(String.valueOf(40));
                }
                pulseWidthSim = 320;
            } else if (localRange > 500 && localRange <= 1000) {
                range = (0x22);
                rangeState = 2;
                gain = 13;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 80;
                    etPulseWidth.setText(String.valueOf(80));
                }
                pulseWidthSim = 320;
            } else if (localRange > 1000 && localRange <= 2000) {
                range = (0x33);
                rangeState = 3;
                gain = 10;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 160;
                    etPulseWidth.setText(String.valueOf(160));
                }
                pulseWidthSim = 720;
            } else if (localRange > 2000 && localRange <= 4000) {
                range = (0x44);
                rangeState = 4;
                gain = 10;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 320;
                    etPulseWidth.setText(String.valueOf(320));
                }
                pulseWidthSim = 2560;
            } else if (localRange > 4000 && localRange <= 8000) {
                range = (0x55);
                rangeState = 5;
                gain = 10;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 640;
                    etPulseWidth.setText(String.valueOf(640));
                }
                pulseWidthSim = 3600;
            } else if (localRange > 8000 && localRange <= 16000) {
                range = (0x66);
                rangeState = 6;
                gain = 9;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 1280;
                    etPulseWidth.setText(String.valueOf(1280));
                }
                pulseWidthSim = 7120;
            } else if (localRange > 16000 && localRange <= 32000) {
                range = (0x77);
                rangeState = 7;
                gain = 9;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 2560;
                    etPulseWidth.setText(String.valueOf(2560));
                }
                pulseWidthSim = 10200;
            } else if (localRange > 32000) {
                range = (0x88);
                rangeState = 8;
                gain = 9;
                if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
                }
                if (!hasSavedPulseWidth) {
                    pulseWidth = 5120;
                    etPulseWidth.setText(String.valueOf(5120));
                }
                pulseWidthSim = 10200;
            }
        } else {
            //?????????????????????
            range = (0x11);
            rangeState = 1;
            if (Constant.CurrentUnit == FT_UNIT) {
                tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
            } else {
                tvRangeValue.setText(getResources().getString(R.string.btn_500m));
            }
            //GC20200331
            if (!hasSavedPulseWidth) {
                pulseWidth = 40;
                etPulseWidth.setText(String.valueOf(40));
            }
            //GC20200527
            pulseWidthSim = 320;
        }
        Constant.RangeValue = range;
        //GC20200428
        selectWaveLength();

    }

    /**
     * ???????????????????????????????????????????????????????????? //GC20200428
     */
    private void selectWaveLength() {
        if (mode == TDR) {
            waveLen = READ_TDR_SIM[rangeState] + 9;
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            waveLen = READ_ICM_DECAY[rangeState] + 9;
        } else if (mode == SIM) {
            waveLen = (READ_TDR_SIM[rangeState] + 9) * 9;
        }
        Log.e("#????????????????????????", " ????????????:" + waveLen);
    }

    /**
     * ?????????????????????
     */
    public void initFrame() {
        Constant.ModeValue = TDR;
        tvGainValue.setText(String.valueOf(gain));
        Constant.Gain = gain;
        velocity = getLocalValue();
        setVelocity(velocity);
        Constant.Velocity = velocity;
        tvBalanceValue.setText(String.valueOf(balance));
        tvZoomValue.setText("1 : " + density);
        tvDelayValue.setText(delay + "??s");
        //?????????????????????
        calculateDistance(Math.abs(pointDistance - zero));
        //?????????????????????????????????    //GC20190708
        tvInformation.setVisibility(View.GONE);
        //SIM??????????????????????????????????????????    //GC20190712
        simOriginalZero = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_CURSOR_POSITION, 12);
        //SIM?????????????????????????????????   //GC20200612
        simStandardZero = StateUtils.getInt(ModeActivity.this, AppConfig.CURRENT_CURSOR_POSITION, 12);
        //?????????????????????    //GC20200103
        leadLength = getLocalLength();
        leadVop = getLocalVop();
        //????????????????????????????????? //GC20200314
        if (batteryValue == 0) {
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_zero);
        } else if (batteryValue == 1) {
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_one);
        } else if (batteryValue == 2) {
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_two);
        } else if (batteryValue == 3) {
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_three);
        } else if (batteryValue == 4) {
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_four);
        } else if (batteryValue == -1) {
            //???WIFI????????????
            ivBatteryStatus.setImageResource(R.drawable.ic_battery_no);
        }

    }

    /**
     * ???????????????????????????
     */
    private void setChartListener() {
        //?????????????????????????????????????????????????????????
        mainWave.setScrubListener(new SparkView.OnScrubListener() {
            @Override
            public void onActionDown(Object x, float y) {
                Log.e("????????????????????????", "onActionDown:" + " /X:"  + x + " /Y:" + y);
                //????????????????????????????????????
                currentActionDownX = (Integer) x;
            }
            @Override
            public void onScrubbed(Object value, float y) {
                //y????????????????????????????????????  //GC20200528
                if (y < 600) {
                    if ((Integer) value <= 510) {
                        //????????????
                        mainWave.setScrubLineVirtual((Integer) value);
                        //????????????????????????
                        positionVirtualChange = (int) value - positionVirtual;
                        Log.e("???????????????????????????", "????????????" + value + " /?????????positionVirtual:" + positionVirtual + " /??????????????????:" + positionVirtualChange);
                        //?????????????????????????????????????????????
                        pointDistance = pointDistance + positionVirtualChange * density;
                        //GT20200619
                        /*int height;
                        if (mode == SIM) {
                            height = Constant.SimData[pointDistance];
                        } else {
                            height = Constant.WaveData[pointDistance];
                        }
                        Log.e("????????????", "???????????????" + height);
                        tvHeight.setText("??????" + height);*/
                        //??????????????????????????????
                        positionVirtual = (int) value;
                        //??????????????????????????? //GC20200611
                        /*int waveDataStart = currentMoverPosition510 * dataLength / 510;
                        Log.e("???????????????", "???????????????value:" + value + "/positionVirtual:" + positionVirtual + "/positionVirtualChange:" + positionVirtualChange + "/pointDistance" + pointDistance + "/zero:" + zero + "/waveDataStart:" + waveDataStart);
                        if (positionVirtual == 0 && zero == 0 && waveDataStart < 510) {
                            pointDistance = 0;
                        }*/
                        //????????????
                        calculateDistance(Math.abs(pointDistance - zero));
                    }
                } else {
                    Log.e("????????????????????????", "value???" + value + " /currentActionDownX???" + currentActionDownX);
                    //???????????????????????????
                    moverMoveValue = (Integer) value - currentActionDownX;
                    //?????????????????????510??????????????????
                    currentMoverPosition510 = currentMoverPosition510 + moverMoveValue;
                    Log.e("????????????????????????", "??????????????????510???????????????" + currentMoverPosition510);
                    currentStart = currentMoverPosition510 * densityMax;
                    //??????x??????????????????0?????????X??????+????????????????????????????????????????????????????????????
                    if (currentMoverPosition510 >= 0 && (currentMoverPosition510 + (mvWave.getWidth() * 510 / mvWave.getParentWidth())) <= 510 && density != densityMax) {
                        //????????????????????????
                        organizeZoomWaveData(currentStart);
                        try {
                            organizeCompareWaveData(currentStart);
                        } catch (Exception ignored) {
                        }
                        //??????????????????
                        displayWave();
                        //???????????????????????????????????????     //GC20200611   ????????????
                        if ( (zero < currentStart) || (zero >= currentStart + 510 * density) ){
                            mainWave.setScrubLineRealDisappear();
                        } else {
                            positionReal = (zero - currentStart) / density;
                            mainWave.setScrubLineReal(positionReal);
                        }
                        //????????????????????????????????????????????????????????????????????????????????????
                        positionVirtual = (pointDistance - currentStart) / density;
                        if ( (pointDistance < currentStart) || (pointDistance >= currentStart + 510 * density) ){
                            mainWave.setScrubLineVirtualDisappear();
                        } else {
                            mainWave.setScrubLineVirtual(positionVirtual);
                        }
                        //??????????????????????????????    //GC20200330
                        if (mode ==SIM) {
                            if ( (simStandardZero < currentStart) || (simStandardZero >= currentStart + 510 * density) ){
                                mainWave.setScrubLineSimDisappear();
                            } else {
                                positionSim = simStandardZero / density;
                                mainWave.setScrubLineSim(positionSim);
                            }
                        }

                        //????????????????????????????????????????????????
                        int moverPosition;
                        moverPosition = mvWave.getParentWidth() * currentMoverPosition510 / 510;
                        setMoverPosition(moverPosition);
                    } else {
                        currentMoverPosition510 = currentMoverPosition510 - moverMoveValue;
                    }
                    currentActionDownX = (Integer) value;
                }
            }
        });

    }

    //???????????????
    private void initMode() {
        switch (mode) {
            case TDR:
                initTDRView();
                break;
            case ICM:
                initICMSURGEView();
                break;
            case ICM_DECAY:
                initICMDECAYView();
                break;
            case SIM:
                initSIMView();
                break;
            case DECAY:
                initDecayView();
                break;
            default:
                break;
        }
    }

    /**
     * ????????????????????????
     */
    private void initBroadcastReceiver() {
        IntentFilter ifDisplay = new IntentFilter();
        ifDisplay.addAction(BROADCAST_ACTION_DEVICE_CONNECTED);
        ifDisplay.addAction(BROADCAST_ACTION_DEVICE_CONNECT_FAILURE);
        ifDisplay.addAction(BROADCAST_ACTION_DOWIFI_COMMAND);
        ifDisplay.addAction(BROADCAST_ACTION_DOWIFI_WAVE);
        ifDisplay.addAction(DISPLAY_ACTION);
        registerReceiver(receiver, ifDisplay);
        //???????????????????????????????????????????????????????????????????????????????????????
        if (ConnectService.isConnected) {
            Intent intent = new Intent(BROADCAST_ACTION_DEVICE_CONNECTED);
            sendBroadcast(intent);
        }
    }

    /**
     * ??????APP?????????????????????
     */
    private void doWifiWave(int[] wifiArray) {
        if (wifiArray[3] == WAVE_TDR_ICM_DECAY) {
            //?????????????????????
          //  for(int i=5;i<8;i++){    //jk20200821
         //          wifiArray[i]=128;
          //  }
            System.arraycopy(wifiArray, 8, waveArray, 0, dataMax);
          // System.arraycopy(wifiArray, 5, waveArray , 0, dataMax);       //jk20200821
            Constant.WaveData = waveArray;
            //GC20191231
            if (mode == ICM){
                icmAutoTest();
            } else if (mode == ICM_DECAY) {
                icmAutoTestDC();    //GC20200109 ??????DC????????????????????????
            }else if(mode == TDR){
                //????????????????????????????????????    //GC20200916
                if (!isLongClick) {
                    tdrAutoTest();
                }
            }
            //?????????????????????
            handler.sendEmptyMessage(VIEW_REFRESH);
        } else if (wifiArray[3] == WAVE_SIM
                || wifiArray[3] == 0x88 || wifiArray[3] == 0x99 || wifiArray[3] == 0xAA || wifiArray[3] == 0xBB
                || wifiArray[3] == 0xCC || wifiArray[3] == 0xDD || wifiArray[3] == 0xEE || wifiArray[3] == 0xFF) {
            //??????????????????
            if (wifiArray[3] == WAVE_SIM) {
                System.arraycopy(wifiArray, 8, waveArray, 0, dataMax);
                Constant.WaveData = waveArray;
                //??????????????????    //GC20200529
                simSum[0] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[0] += waveArray[i];
                }
                Log.e("???MIM???", "???1???");
            }
            if (wifiArray[3] == 0x88) {
                System.arraycopy(wifiArray, 8, simArray1, 0, dataMax);
                Constant.TempData1 = simArray1;
                simSum[1] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[1] += simArray1[i];
                }
                Log.e("???MIM???", "???2???");
            }
            if (wifiArray[3] == 0x99) {
                System.arraycopy(wifiArray, 8, simArray2, 0, dataMax);
                Constant.TempData2 = simArray2;
                simSum[2] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[2] += simArray2[i];
                }
                Log.e("???MIM???", "???3???");
            }
            if (wifiArray[3] == 0xAA) {
                System.arraycopy(wifiArray, 8, simArray3, 0, dataMax);
                Constant.TempData3 = simArray3;
                simSum[3] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[3] += simArray3[i];
                }
                Log.e("???MIM???", "???4???");
            }
            if (wifiArray[3] == 0xBB) {
                System.arraycopy(wifiArray, 8, simArray4, 0, dataMax);
                Constant.TempData4 = simArray4;
                simSum[4] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[4] += simArray4[i];
                }
                Log.e("???MIM???", "???5???");
            }
            if (wifiArray[3] == 0xCC) {
                System.arraycopy(wifiArray, 8, simArray5, 0, dataMax);
                Constant.TempData5 = simArray5;
                simSum[5] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[5] += simArray5[i];
                }
                Log.e("???MIM???", "???6???");
            }
            if (wifiArray[3] == 0xDD) {
                System.arraycopy(wifiArray, 8, simArray6, 0, dataMax);
                Constant.TempData6 = simArray6;
                simSum[6] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[6] += simArray6[i];
                }
                Log.e("???MIM???", "???7???");
            }
            if (wifiArray[3] == 0xEE) {
                System.arraycopy(wifiArray, 8, simArray7, 0, dataMax);
                Constant.TempData7 = simArray7;
                simSum[7] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[7] += simArray7[i];
                }
                Log.e("???MIM???", "???8???");
            }
            if (wifiArray[3] == 0xFF) {
                System.arraycopy(wifiArray, 8, simArray8, 0, dataMax);
                Constant.TempData8 = simArray8;
                //???????????????????????????????????????    //GC20200601
                if (density < densityMax) {
                    organizeZoomWaveData(currentStart);
                } else {
                    organizeWaveData();
                }
                simSum[8] = 0;
                for (int i = 0; i < dataMax; i++) {
                    simSum[8] += simArray8[i];
                }
                //SIM????????????????????????   //GC20200529
                receiveSimOver = true;
                Log.e("???MIM???", "???9???");
            }
            if (receiveSimOver) {
                //SIM?????????????????? //GC20200529
                selectBestSim();
                handler.sendEmptyMessage(VIEW_REFRESH);
                receiveSimOver = false;
            }
        }

    }

    //????????????????????????????????????????????????172????????????????????????????????????????????????????????????????????????????????????????????????????????????
    private double getLocalValue() {
        double vopValue = 172;
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        if (paramInfo != null) {
            if (paramInfo.getCableVop() != null && !TextUtils.isEmpty(paramInfo.getCableVop())) {
                //????????????????????????  //20200522
                vopValue = Double.valueOf(paramInfo.getCableVop());
            }
        }
        if (vopValue == 0 || vopValue == 0.0) {
            vopValue = 172;
        }
        return vopValue;
    }

    /**
     * ????????????????????????????????????
     */
    private double getLocalLength() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        if (paramInfo != null) {
            if (paramInfo.getLength() != null && !TextUtils.isEmpty(paramInfo.getLength())) {
                if (Constant.CurrentSaveUnit == MI_UNIT) {
                    leadLength = Double.valueOf(paramInfo.getLength());
                } else {
                    leadLength = Double.valueOf(UnitUtils.ftToMi(Double.valueOf(paramInfo.getLength())));
                }
            }
        }
        return leadLength;
    }

    /**
     * ????????????????????????????????????
     */
    private double getLocalVop() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        if (paramInfo != null) {
            if (paramInfo.getVop() != null && !TextUtils.isEmpty(paramInfo.getVop())) {
                if (Constant.CurrentSaveUnit == MI_UNIT) {
                    leadVop = Double.valueOf(paramInfo.getVop());
                } else {
                    leadVop = Double.valueOf(UnitUtils.ftToMi(Double.valueOf(paramInfo.getVop())));
                }
            }
        }
        if (leadVop == 0 || leadVop == 0.0) {
            leadVop = 172;
        }
        return leadVop;
    }

    /**
     * ??????APP???????????????
     */
    private void doWifiCommand(int[] wifiArray) {
        //????????????????????????APP????????????????????????
        if ((wifiArray[5] == COMMAND_TRIGGER) && (wifiArray[6] == TRIGGERED)) {
            command = COMMAND_RECEIVE_WAVE;
            dataTransfer = mode;
            //????????????
            startService();
            //?????????????????????   //20200523
            alreadyDisplayWave = false;
            // Log.e("??????????????????", "??????????????????????????????");
            ConnectService.canAskPower = false;
            if (tDialog != null) {
                tDialog.dismiss();
            }
            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.receiving_data)
                    .setScreenWidthAspect(this, 0.25f)
                    .setCancelableOutside(false)
                    .create()
                    .show();
            Log.e("DIA", " ????????????????????????" + " ICM/SIM/DECAY");
        } else if (wifiArray[5] == POWER_DISPLAY) {
            int batteryValue = wifiArray[6] * 256 + wifiArray[7];
            if (batteryValue <= 2600) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_zero);
            } else if (batteryValue > 2600 && batteryValue <= 2818) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_one);
            } else if (batteryValue > 2818 && batteryValue <= 3018) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_two);
            } else if (batteryValue > 3018 && batteryValue <= 3120) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_three);
            } else if (batteryValue > 3120) {
                ivBatteryStatus.setImageResource(R.drawable.ic_battery_four);
            }
        }
        //TODO 20200407 ?????????????????????????????????????????????
        if (!Constant.isTesting) {
            ConnectService.canAskPower = true;
            Log.e("??????????????????????????????", "???????????????????????????????????????????????????????????????");
        }

    }

    private void initTDRView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_tdr));
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    private void initICMSURGEView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_icm));
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    private void initICMDECAYView() {
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        //?????????????????????????????????   ????????????  //GC20200525
        tvBalanceSpace.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);

        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvMode.setText(getResources().getText(R.string.btn_icm_decay));
        tvWaveValue.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    private void initSIMView() {
        tvMode.setText(getResources().getText(R.string.btn_sim));
        viewMoveVerticalWave.setVisibility(View.VISIBLE);
        tvWaveNext.setVisibility(View.VISIBLE);
        tvWavePre.setVisibility(View.VISIBLE);

        tvBalanceMin.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvCompare.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.VISIBLE);
        tvDelayText.setVisibility(View.VISIBLE);
        tvPulseWidth.setVisibility(View.GONE);

        //SIM????????????????????????   //20200521
        tvGainAdd.setImageResource(R.drawable.bg_gain_plus_s_selector);
        tvGainMin.setImageResource(R.drawable.bg_gain_min_s_selector);
        tvOrigin.setImageResource(R.drawable.bg_origin_s_selector);
        tvTriggerDelay.setImageResource(R.drawable.bg_trigger_delay_s_selector);
        tvCal.setImageResource(R.drawable.bg_cal_s_selector);
        tvRange.setImageResource(R.drawable.bg_range_s_selector);
        //SIM???????????????????????????   //GC20200604
        tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_false);
        tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_false);
        tvFile.setImageResource(R.drawable.bg_file_s_selector);

        tvRecordsSave.setImageResource(R.drawable.bg_save_s_selector);
        tvFileRecords.setImageResource(R.drawable.bg_records_s_selector);
        tvSave.setImageResource(R.drawable.bg_save_s_selector);
        tvVopMin.setImageResource(R.drawable.bg_vop_min_s_selector);
        tvVopPlus.setImageResource(R.drawable.bg_vop_plus_s_selector);
        tvDelayMin.setImageResource(R.drawable.bg_delay_min_s_selector);
        tvDelayPlus.setImageResource(R.drawable.bg_delay_plus_s_selector);
    }

    private void initDecayView() {
        tvMode.setText(getResources().getText(R.string.btn_decay));
        viewMoveVerticalWave.setVisibility(View.GONE);
        tvWaveNext.setVisibility(View.GONE);
        tvWavePre.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceSpace.setVisibility(View.GONE);
        tvDelayValue.setVisibility(View.GONE);
        tvDelayText.setVisibility(View.GONE);
        tvDelaySpace.setVisibility(View.GONE);
        tvBalancePlus.setVisibility(View.GONE);
        tvBalanceMin.setVisibility(View.GONE);
        tvBalanceText.setVisibility(View.GONE);
        tvBalanceValue.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvOrigin.setVisibility(View.GONE);
        tvPulseWidth.setVisibility(View.GONE);
        tvTriggerDelay.setVisibility(View.GONE);
        tvWaveText.setVisibility(View.GONE);
        tvWaveValue.setVisibility(View.GONE);
        tvWaveSpace.setVisibility(View.GONE);
    }

    /**
     * ????????????  //GC20190709   //GC20191231 ??????????????????
     */

    private void calculateDistance(int cursorDistance) {
        double distance;
        int k = 1;
        int l;
        int lFault;

        //?????????????????????range=6(32km)???range=7(64km)??????25M???????????????????????????????????????100M??????????????????????????????????????????????????????4???
        if (((mode == DECAY) || mode == ICM || mode == ICM_DECAY) && (rangeState > 6)) {
            k = 4;
        }

        distance = (((double) cursorDistance * velocity) * k) / 2 * 0.01;
        if (mode == DECAY) {
            //DECAY????????????/2
            distance = (((double) cursorDistance * velocity / 2) * k) / 2 * 0.01;
        } else if ((mode == TDR) || (mode == SIM)) {
            //250m????????????/2  //GC20191225
            if (range == RANGE_250) {
                distance = (((double) cursorDistance * velocity / 2) * k) / 2 * 0.01;
            }
           } else if ((mode == ICM) || (mode == ICM_DECAY)) {
            //???????????????     //GC20200103
            Log.e("leadCat", "leadCat" + MainActivity.leadCat);
          if ((leadLength > 0) && (MainActivity.leadCat == 1)) {  //jk20201130  ???????????????????????????????????????//if (leadLength > 0) {
                //????????????
                l = (int) (leadLength * 2000 / leadVop / 10);
                lFault = cursorDistance - l;
                distance = (((double) lFault * velocity) * k) / 2 * 0.01 + leadLength;
            }

        }

        //TODO 2019-1223-0947 ?????????????????????????????????????????????????????????
        Constant.CurrentLocation = distance;
        //??????????????????
        if (Constant.CurrentUnit == MI_UNIT) {
            tvDistance.setText(new DecimalFormat("0.00").format(distance));
        } else {
            tvDistance.setText(UnitUtils.miToFt(distance));
        }

    }

    //??????????????????
    private void setMoveView() {
        RectF sparkViewRectf = myChartAdapterMainWave.getDataBounds();
        viewMoveVerticalWave.setSparkViewRect(sparkViewRectf);
        //????????????view?????????view
        mvWave.setParentView(llHorizontalView);

    }

    /**
     * TDR??????????????????
     */
    private void tdrAutoTest() {
        gainJudgmentTdr1();
        switch (gainState) {
            case 0:
                tvInformation.setText("");
                break;
            case 1:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_high));
                return;
            case 2:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_low));
                return;
            default:
                break;
        }
        tdrCurveFitting();
        tdtAutoCursor();

        //??????????????????
        step = 8;
        count = 6;
        //??????????????????
        isLongClick = false;

    }

    /**
     * TDR??????????????????
     */
    private void tdrCurveFitting(){
        /*???????????????????????????????????????*/
        //??????????????????????????????????????????
        findExtremePoint();
    /*   if (range == RANGE_250) {   //jk20200826
           g = 2 * g;
            u = 2 * u;
         } else {
        }*/
        double[] waveArray1 = new double[60050];
            //?????????128?????????
            for (int j = u; j < g; j++) {
                //waveArray1[j] = waveArray[j] - 133;  //jk20201022 ?????????133?????????
                waveArray1[j] = waveArray[j] - Median_value; //jk20210520
            }
            //??????????????????
            double[] X = new double[60050];
            double[] Y = new double[60050];
            double[] atemp = new double[8];
            double[] b = new double[4];
            double[][] a = new double[4][4];

            for (int h = u; h < g; h++) {
                X[h - u] = h - u;
                Y[h - u] = waveArray1[h];
            }
            for (int i = 0; i < g - u; i++) {
                atemp[1] += X[i];
                atemp[2] += Math.pow(X[i], 2);
                atemp[3] += Math.pow(X[i], 3);
                atemp[4] += Math.pow(X[i], 4);
                atemp[5] += Math.pow(X[i], 5);
                atemp[6] += Math.pow(X[i], 6);
                b[0] += Y[i];
                b[1] += X[i] * Y[i];
                b[2] += Math.pow(X[i], 2) * Y[i];
                b[3] += Math.pow(X[i], 3) * Y[i];
            }

            atemp[0] = g - u;

            for (int i1 = 0; i1 < 4; i1++) {
                int k = i1;
                for (int j = 0; j < 4; j++) {
                    a[i1][j] = atemp[k++];
                }
            }

            for (int k = 0; k < 3; k++) {
                int column = k;
                double mainelement = a[k][k];
                for (int i2 = k; i2 < 4; i2++) {
                 /*if (fabs(a[i2][k] > mainelement)) {
                    mainelement = fabs(a[i2][k]);
                    column = i2;
                 }*/
                    if (Math.abs((a[i2][k])) > mainelement) {
                        mainelement = Math.abs((a[i2][k]));
                        column = i2;
                    }
                }

                for (int j = k; j < 4; j++) {
                    double atemp_1 = a[k][j];
                    a[k][j] = a[column][j];
                    a[column][j] = atemp_1;
                }

                double btemp = b[k];
                b[k] = b[column];
                b[column] = btemp;

                for (int i3 = k + 1; i3 < 4; i3++) {
                    double Mik = a[i3][k] / a[k][k];
                    for (int j = k; j < 4; j++) {
                        a[i3][j] -= Mik * a[k][j];
                    }
                    b[i3] -= Mik * b[k];
                }
            }

            b[3] /= a[3][3];

            for (int i = 2; i >= 0; i--) {
                double sum = 0;
                for (int j = i + 1; j < 4; j++) {
                    sum += a[i][j] * b[j];
                }
                b[i] = (b[i] - sum) / a[i][i];
            }

        /*    int point = 0;
            int point1 = 0;
            int point2 = 0;
            int point3 = 0;
            int point4 = 0;

            int p1 = 0;
            int p2 = 0;
            int p3 = 0;
            int p4 = 0;
            int p5 = 0;*/

        //??????????????????????????????????????????0?????????????????????  ????????????????????????  //jk20210527
        autoLocation1 = equationSolving(b[3],b[2],b[1],b[0],-10,10) + u;

      /*      double[] mat_sum = new double[60050];
            double[] mat_sum1 = new double[60050];

            for (int y = 0; y < g - u; y++) {
                mat_sum[y] = b[3] * y * y * y + b[2] * y * y + b[1] * y + b[0];
            }

            for (int i = 0; i < g - u - 1; i++) {
               // if ((((mat_sum[i]) <= 0) && ((mat_sum[i + 1]) >= 0)) || ((mat_sum[i]) >= 0) && ((mat_sum[i + 1]) <= 0)) {
                if ((((mat_sum[i]) <= 0) && ((mat_sum[i + 1]) >= 0)) || ((mat_sum[i]) >=0) && ((mat_sum[i + 1]) <= 0)) {
                    //Log.e("1", " /zou ");
                    p1++;
                    int z = i;
                    point = z + u + 1;
                    // Log.e("1", " /???????????? = " + point);
                } else {
                    for (int f = 0; f < g - u - 1; f++) {
                        if ((((mat_sum[f]) <= 6) && ((mat_sum[f + 1]) >= 6)) || (((mat_sum[f]) >= 6) && ((mat_sum[f + 1]) <= 6))) {
                            // Log.e("2", " /zou ");
                            p2++;
                            int z1 = f;
                            // printf("z=%d\n", z);
                            point1 = z1 + u + 1;
                            //  Log.e("2", " /???????????? = " + point1);
                        }
                    }
                }
            }
            //??????        //?????????  ?????????  ??????20200703
            if (p1 == 0 && p2 == 0 ) {
                if (u >= 4) {
                    //  Log.e("4", " /zou ");
                    int sum_s1 = 0;
                    for (int q = 5; q < 15; q++) {
                        sum_s1 = sum_s1 + waveArray[q];
                    }
                    sum_s1 = sum_s1 / 10;
                    for (int j = u; j < g; j++) {
                        waveArray1[j] = waveArray[j] - sum_s1;
                    }
                    for (int h = u; h < g; h++) {
                        X[h - u] = h - u;
                        Y[h - u] = waveArray1[h];
                    }
                    for (int i = 0; i < g - u; i++) {
                        atemp[1] += X[i];
                        atemp[2] += Math.pow(X[i], 2);
                        atemp[3] += Math.pow(X[i], 3);
                        atemp[4] += Math.pow(X[i], 4);
                        atemp[5] += Math.pow(X[i], 5);
                        atemp[6] += Math.pow(X[i], 6);
                        b[0] += Y[i];
                        b[1] += X[i] * Y[i];
                        b[2] += Math.pow(X[i], 2) * Y[i];
                        b[3] += Math.pow(X[i], 3) * Y[i];
                    }

                    atemp[0] = g - u;

                    for (int i1 = 0; i1 < 4; i1++) {
                        int k = i1;
                        for (int j = 0; j < 4; j++) {
                            a[i1][j] = atemp[k++];
                        }
                    }

                    for (int k = 0; k < 3; k++) {
                        int column = k;
                        double mainelement = a[k][k];
                        for (int i2 = k; i2 < 4; i2++) {
                            if (Math.abs((a[i2][k])) > mainelement) {
                                mainelement = Math.abs((a[i2][k]));
                                column = i2;
                            }
                        }

                        for (int j = k; j < 4; j++) {
                            double atemp_1 = a[k][j];
                            a[k][j] = a[column][j];
                            a[column][j] = atemp_1;
                        }

                        double btemp = b[k];
                        b[k] = b[column];
                        b[column] = btemp;

                        for (int i3 = k + 1; i3 < 4; i3++) {
                            double Mik = a[i3][k] / a[k][k];
                            for (int j = k; j < 4; j++) {
                                a[i3][j] -= Mik * a[k][j];
                            }
                            b[i3] -= Mik * b[k];
                        }
                    }

                    b[3] /= a[3][3];

                    for (int i = 2; i >= 0; i--) {
                        double sum = 0;
                        for (int j = i + 1; j < 4; j++) {
                            sum += a[i][j] * b[j];
                        }
                        b[i] = (b[i] - sum) / a[i][i];
                    }

                    for (int y = 0; y < g - u; y++) {
                        mat_sum[y] = b[3] * y * y * y + b[2] * y * y + b[1] * y + b[0];
                    }

                    for (int i = 0; i < g - u - 1; i++) {
                        if ((((mat_sum[i]) <= 0) && ((mat_sum[i + 1]) >= 0)) || ((mat_sum[i]) >= 0) && ((mat_sum[i + 1]) <= 0)) {
                            p4++;
                            int z = i;
                            point3 = z + u + 1;
                            // Log.e("1", " /???????????? = " + point3);
                        }
                    }
                }
                if (p4 == 0) {
                    //  Log.e("3", " /zou ");
                    int sum_s = 0;
                    for (int q = 1; q < 10; q++) {
                        sum_s = sum_s + waveArray[q];
                    }
                    sum_s = sum_s / 9;
                    // Log.e("sum", " / = " + sum_s);
                    for (int j = 0; j < u + 7; j++) {
                        waveArray1[j] = waveArray[j] - sum_s; // waveArray1[j] = waveArray1[j] - 128;       //tdr[j] = tdr[j] - sum;   //???128?????????
                        //?????????????????????opencv??????????????????
                        //printf("tdr=%d\n", tdr[j]);
                    }

                    for (int h = 0; h < u + 7; h++) {
                        X[h] = h;
                        Y[h] = waveArray1[h];
                    }

                    for (int i = 0; i < u + 7; i++) {
                        atemp[1] += X[i];
                        atemp[2] += Math.pow(X[i], 2);
                        atemp[3] += Math.pow(X[i], 3);
                        atemp[4] += Math.pow(X[i], 4);
                        atemp[5] += Math.pow(X[i], 5);
                        atemp[6] += Math.pow(X[i], 6);
                        b[0] += Y[i];
                        b[1] += X[i] * Y[i];
                        b[2] += Math.pow(X[i], 2) * Y[i];
                        b[3] += Math.pow(X[i], 3) * Y[i];
                    }

                    atemp[0] = u + 7;

                    for (int i = 0; i < 4; i++) {
                        int k = i;
                        for (int j = 0; j < 4; j++) {
                            a[i][j] = atemp[k++];
                        }
                    }

                    for (int k = 0; k < 3; k++) {
                        int column = k;
                        double mainelement = a[k][k];
                        for (int i = k; i < 4; i++) {
                            if (Math.abs(a[i][k]) > mainelement) {
                                mainelement = Math.abs(a[i][k]);
                                column = i;
                            }
                        }

                        for (int j = k; j < 4; j++) {
                            double atemp2 = a[k][j];
                            a[k][j] = a[column][j];
                            a[column][j] = atemp2;
                        }

                        double btemp = b[k];
                        b[k] = b[column];
                        b[column] = btemp;

                        for (int i = k + 1; i < 4; i++) {
                            double Mik = a[i][k] / a[k][k];
                            for (int j = k; j < 4; j++) {
                                a[i][j] -= Mik * a[k][j];
                            }
                            b[i] -= Mik * b[k];
                        }
                    }

                    b[3] /= a[3][3];

                    for (int i = 2; i >= 0; i--) {
                        double sum = 0;
                        for (int j = i + 1; j < 4; j++) {
                            sum += a[i][j] * b[j];
                        }
                        b[i] = (b[i] - sum) / a[i][i];
                    }

                    for (int y = 0; y < u + 7; y++) {
                        mat_sum1[y] = b[3] * y * y * y + b[2] * y * y + b[1] * y + b[0];
                    }
                    for (int i = 0; i < u + 7 - 1; i++) {
                        if ((((mat_sum1[i]) <= 0) && ((mat_sum1[i + 1]) >= 0)) || (((mat_sum1[i]) >= 0) && ((mat_sum1[i + 1]) <= 0))) {
                            p3++;
                            int z = i;
                            point2 = z + 1;
                            //  Log.e("3", " /???????????? = " + point2);
                        }
                    }
                }
            }

            if (p1 > 0) {
                autoLocation1 = point;
            } else if (p2 > 0) {
                autoLocation1 = point1;
            } else if (p3 > 0) {
                autoLocation1 = point2;
            }  else{
                autoLocation1 = point3;
            }*/

        if(autoLocation1 <=tdrPointuse[rangeState]+3)  //jk20200923
        {
            autoLocation1 = 0;
        }
            Log.e("4", " /???????????? = " + autoLocation1);
        //jk20210305 ????????????????????????????????????????????????????????????????????????????????????????????????????????????
        //????????????????????????5??????
        int Ln;
        int waveArraySum = 0;
        int waveArraySum1 = 0;

        if(autoLocation1 >=tdrPoint[rangeState]) {
            for (Ln = autoLocation1; Ln > autoLocation1 - tdrPoint[rangeState]; Ln--) {
                waveArraySum = waveArray[Ln] + waveArraySum;
            }

            waveArraySum =  waveArraySum / tdrPoint[rangeState];
            //Log.e("waveArraySum", " /waveArraySum = " + waveArraySum);

           // waveArraySum =  Math.abs( waveArraySum - 132);
            waveArraySum =  Math.abs( waveArraySum - Median_value); //jk20210520
            Log.e("waveArraySum", " /waveArraySum = " + waveArraySum);
            Log.e("pointflag", " /pointflag = " + pointflag);
            if (waveArraySum >= 10) {
                if (pointflag == 1) {
                    point_s1();
                } else {
                    point_x1();
                }
                Log.e("point_s_pos", " /point_s_pos = " + point_s_pos);
                if (point_s_pos <= pulsetdrRemove1[rangeState]) {
                    autoLocation = 0;
                }
            } else {
                    autoLocation = autoLocation1;
            }

        } else{
            if(autoLocation1 != 0){
            /*    for (Ln = autoLocation1; Ln > 0; Ln--) {
                    waveArraySum1 = waveArray[Ln] + waveArraySum1;
                }
                waveArraySum1 =  waveArraySum1 / autoLocation1;
                Log.e("waveArraySum", " /waveArraySum = " + waveArraySum);

              //  waveArraySum1 =  Math.abs( waveArraySum1 - 132);
                waveArraySum1 =  Math.abs( waveArraySum1 - Median_value); //jk20210520

                if (waveArraySum1 >= 10){
                    autoLocation = 0;
                }else {
                    autoLocation = autoLocation1;
                }*/
                autoLocation = autoLocation1;
            }else{
                autoLocation = 0;
            }
        }
        Log.e("autoLocation", " /???????????? = " + autoLocation);
        }

    /*
     ** ????????????u??????????????????  ????????????????????????  ???????????????
     */
    int point_s_pos;
    private void point_s1(){
        //??????????????????
        int j = 3;
        int maxNum = 0;
        int[] maxData = new int[65560];
        int[] maxDataPos = new int[65560];
        int max = maxData[0];
        int maxPos = maxDataPos[0];
        //?????????????????????????????????????????????????????????????????????
        while ( (j >=  3) && (j < u) ) {
            if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                if (waveArray[j - 1] >= waveArray[j - 2]) {
                    if (waveArray[j - 2] > waveArray[j - 3]) {
                        maxData[maxNum] = waveArray[j];
                        maxDataPos[maxNum] = j;
//                            Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                        maxNum++;
                    }
                }
            }
            j++;
        }

        if (maxNum == 0) {
            Log.e("tdr", "???????????????");
            // tvInformation.setVisibility(View.VISIBLE);
            //  tvInformation.setText(getResources().getString(R.string.testAgain));
        }else {
            for (int k = 0; k < maxNum; k++) {
                if (maxData[k] >= max) {
                    max = maxData[k];
                    maxPos = maxDataPos[k];
                }
            }
            point_s_pos = maxPos;

            // Log.e("1", " /????????????????????? = " + maxPos);
        }




    }
    private void point_x1() {
        int i1 = 5;
        int minNum1 = 0;
        int[] minData1 = new int[65560];
        int[] minDataPos1 = new int[65560];
        int minPos=minDataPos1[0];

        while ( (i1 >= 5 ) && (i1 < u) ) {
            if ((waveArray[i1] <= waveArray[i1 - 1]) && (waveArray[i1] <= waveArray[i1 + 1])) {
                if (waveArray[i1 - 1] <= waveArray[i1 - 2]) {
                    if (waveArray[i1 - 2] <= waveArray[i1 - 3]) {
                        if (waveArray[i1 - 3] <= waveArray[i1 - 4]) {
                            if (waveArray[i1 - 4] <= waveArray[i1 - 5]) {
                                minData1[minNum1] = waveArray[i1];
                                minDataPos1[minNum1] = i1;
                                minNum1++;
                            }
                        }
                    }
                }
            }
            i1++;
        }

        if (minNum1 > 0) {
            int min1 = minData1[0];
            for (int k1 = 0; k1 < minNum1; k1++) {
                if (minData1[k1] <= min1) {
                    min1 = minData1[k1];
                    minPos = minDataPos1[k1];
                }
            }

        }

        point_s_pos = minPos;
    }


    /**
     * TDR??????????????????
     */
    private void tdtAutoCursor() {
        //????????????????????????
            zero = 0;
            if (range == RANGE_250) {
                pointDistance = 2 * autoLocation;
            } else {
                pointDistance = autoLocation;
            }
            if (zero >= (currentMoverPosition510 * dataLength / 510) && zero <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
                mainWave.setScrubLineReal(0);
            } else {
                mainWave.setScrubLineRealDisappear();
            }
            //?????????????????????
      /*  if (autoLocation >= (currentMoverPosition510 * dataLength / 510) && autoLocation <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
            positionVirtual = (autoLocation - (currentMoverPosition510 * dataLength / 510)) / density;
            mainWave.setScrubLineVirtual(positionVirtual);
        } else {
            mainWave.setScrubLineVirtualDisappear();
        }*/  //jk20200826
            if (pointDistance >= (currentMoverPosition510 * dataLength / 510) && pointDistance <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
                positionVirtual = (pointDistance - (currentMoverPosition510 * dataLength / 510)) / density;
                mainWave.setScrubLineVirtual(positionVirtual);
            } else {
                mainWave.setScrubLineVirtualDisappear();
            }
            calculateDistance(Math.abs(pointDistance - zero));
        }

    /**
     * TDR?????????????????????????????????500m??????????????????????????????????????????????????????????????????  //GC20200916
     * ??????1???????????????500m????????????????????????????????????????????????????????????????????????
     */
    private void longTestInit() {
        //????????????500m??????
        if (range == RANGE_500) {
            gain = 13;
            setGain(gain);
        } else {
            setRange(0x11);
            setGain(gain);
            if (!hasSavedPulseWidth) {
                pulseWidth = 40;
                etPulseWidth.setText(String.valueOf(40));
            }
            setPulseWidth(pulseWidth);
        }
        //?????????????????????????????????0-15???
        balance = 8;
        setBalance(balance);
        longTestInit = true;
        handler.postDelayed(ModeActivity.this::clickTest, 100);

    }

    /**
     * ??????????????????????????????  //GC20200917
     */
    private void tdrAutoTestLong() {
        //??????2????????????????????????????????????????????????6???
        while ((count > 0)) {
            count--;
            Log.e("tdr", "count" + count);
            step = step / 2;
            if(step <= 1){
                step = 1;
            }
            //?????????????????????????????????????????????????????????????????????
            findStartExtremePoint();
            balanceAutoTdr();
            switch (balanceState){
                case 0:
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 1:
                    //???????????????????????????????????????????????????????????????
                    balanceState = 0;
                    balance = balance - step;
                    if(balance <0) {
                        balance = 0;
                    }
                    setBalance(balance);
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 2:
                    balanceState = 0;
                    balance = balance + step;
                    if(balance >15){
                        balance = 15;
                    }
                    setBalance(balance);
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                default:
                    break;
            }
        }
        //??????3????????????????????????
        selectRange();
        //??????4????????????????????????
        gainJudgmentTdr();
        switch (gainState) {
            case 0:
                //???????????????????????????????????????
                tvInformation.setText("");
                break;
            case 1:
                tvInformation.setText("");
                gainState = 0;
                gain = gain - 1;
                setGain(gain);
                handler.postDelayed(ModeActivity.this::clickTest, 100);
                return;
            case 2:
                tvInformation.setText("");
                gainState = 0;
                gain = gain + 1;
                setGain(gain);
                handler.postDelayed(ModeActivity.this::clickTest, 100);
                return;
            default:
                break;
        }
        //????????????????????????
        tdrCurveFitting();
        tdtAutoCursor();

        //?????????????????? ????????????
        step = 8;
        count = 6;
        isLongClick = false;  //??????????????????
    }

    /**
     * ??????2??????????????????????????????    //GC20200916
     */
    private void selectBalance() {
        //???????????????6???
        while ((count > 0)) {
            count--;
//            Log.e("tdr", "count" + count);
            step = step / 2;
            if(step <= 1){
                step = 1;
            }
            //?????????????????????????????????????????????????????????????????????
            findStartExtremePoint();
            balanceAutoTdr();
            switch (balanceState){
                case 0:
                    break;
                case 1:
                    //???????????????????????????????????????????????????????????????
                    balanceState = 0;
                    balance = balance - step;
                    if(balance <0) {
                        balance = 0;
                    }
                    setBalance(balance);
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 2:
                    balanceState = 0;
                    balance = balance + step;
                    if(balance >15){
                        balance = 15;
                    }
                    setBalance(balance);
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                default:
                    break;
            }
        }
        //?????????????????? ????????????
        step = 8;
        count = 6;
        balanceIsReady = true;
        handler.postDelayed(ModeActivity.this::clickTest, 100);

    }

    /**
     * ??????3????????????????????????
     */
    int rangeCount = 1;
    private void selectRange() {
        int i;
        int max1 = 0;
        int sub1;
        //?????????????????????????????????   //jk20200904 ?????????????????????????????????120??????????????????
        for (i = pulselongtdrRemove[rangeState] ; i < dataMax - removeTdrSim[rangeState]-30; i++) {  //jk20200917
         //   sub1 = waveArray[i] - 133;
           // sub1 = waveArray[i] - 128;  //jk20210519
            sub1 = waveArray[i] - Median_value; //jl20210520
            if (Math.abs(sub1) > max1) {
                max1 = Math.abs(sub1);
            }
        }
        //???????????????????????????5???????????????????????????????????????????????????????????????
//        if(max1 <= 11) {
        //jk20210309 ?????????????????????????????????????????????????????????????????? ??????????????????
        findExtremePointRange();
        Log.e("rangeAdjust", "rangeAdjust"+rangeAdjust);
        if((rangeAdjust == 1)&&(max1 <= 9)) {
            rangeCount++;
            Log.e("tdr", "rangeCount" + rangeCount);
            switch (rangeCount) {
                case 2 :
                    setRange(0x22);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 80;
                            setPulseWidth(80);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(80));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 3 :
                    setRange(0x33);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 160;
                            setPulseWidth(160);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(160));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 4 :
                    setRange(0x44);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 320;
                            setPulseWidth(320);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(320));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 5 :
                    setRange(0x55);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 640;
                            setPulseWidth(640);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(640));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 6 :
                    setRange(0x66);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 1280;
                            setPulseWidth(1280);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(1280));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 7 :
                    setRange(0x77);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 2560;
                            setPulseWidth(2560);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(2560));
                    }
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    return;
                case 8 :
                    setRange(0x88);
                    setGain(gain);
                    if (!hasSavedPulseWidth && mode == TDR) {
                        handler.postDelayed(() -> {
                            pulseWidth = 5120;
                            setPulseWidth(5120);
                        }, 20);
                        etPulseWidth.setText(String.valueOf(5120));
                    }
                    //G?
                    rangeCount = 1;
                    rangeIsReady = true;
                    handler.postDelayed(ModeActivity.this::clickTest, 100);
                    break;
                default:
                    break;
            }

        }
        //??????????????????
        rangeCount = 1;
        rangeIsReady = true;
        handler.postDelayed(ModeActivity.this::clickTest, 100);

    }
    /*
      ??????????????????????????????  //jk20210309
    */
    private void  findExtremePointRange(){
        //??????????????????
        int j = pulsetdrRemove1[rangeState] + 3;
        int maxNum = 0;
        int[] maxData = new int[65560];
        int[] maxDataPos = new int[65560];
        int max = maxData[0];
        int maxPos = maxDataPos[0];
        int maxjudge = 0;
        int minjudge = 0;
        //?????????????????????????????????????????????????????????????????????
        // while ( (j >= pulsetdrRemove[rangeState] + 3) && (j < dataMax - removeTdrSim[rangeState]) ) {
        while ( (j >= pulsetdrRemove1[rangeState] + 3) && (j < dataMax - removeTdrSim[rangeState]) ) {
            if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                if (waveArray[j - 1] >= waveArray[j - 2]) {
                    if (waveArray[j - 2] > waveArray[j - 3]) {
                        maxData[maxNum] = waveArray[j];
                        maxDataPos[maxNum] = j;
// Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                        maxNum++;
                    }
                }
            }
            j++;
        }

       if (maxNum == 0) {
            Log.e("tdr", "???????????????????????????");
        } else {
            for (int k = 0; k < maxNum; k++) {
                if ( maxDataPos[k] > 2*pulsetdrRemove11[rangeState]) {
                  maxjudge = 1;
                 //   Log.e("tdr", "???????????????????????????t");
                 //   Log.e(" maxDataPos[k]", " maxDataPos[k]"+ maxDataPos[k]);
                }
            }

        }



        int i1 = pulsetdrRemove1[rangeState]+5 ;
        int minNum1 = 0;
        int[] minData1 = new int[65560];
        int[] minDataPos1 = new int[65560];
        int minPos=minDataPos1[0];
        int min1 = waveArray[0];

        // while ( (i1 >= pulsetdrRemove[rangeState] ) && (i1 < dataMax - removeTdrSim[rangeState]) ) {   //jk20200714
        while ( (i1 >= pulsetdrRemove1[rangeState]+5 ) && (i1 < dataMax - removeTdrSim[rangeState]) ) {   //jk20200714
            if ((waveArray[i1] <= waveArray[i1 - 1]) && (waveArray[i1] <= waveArray[i1 + 1])) {
                if (waveArray[i1 - 1] < waveArray[i1 - 2]) {
                    if (waveArray[i1 - 2] < waveArray[i1 - 3]) {
                        if (waveArray[i1 - 3] < waveArray[i1 - 4]) {
                            if (waveArray[i1 - 4] < waveArray[i1 - 5]) {
                                minData1[minNum1] = waveArray[i1];
                                minDataPos1[minNum1] = i1;
                                minNum1++;
                                // Log.e("ceshi", " /??????????????? = " + i1);
                            }
                        }
                    }
                }
            }
            i1++;
        }

        if (minNum1 > 0) {
            for (int k1 = 0; k1 < minNum1; k1++) {
                if ( minDataPos1[k1] > 2*pulsetdrRemove11[rangeState]) {
                   minjudge = 1;
                   // Log.e("tdr", "???????????????????????????t");
                 //   Log.e("minDataPos1[k1]", "minDataPos1[k1]"+ minDataPos1[k1]);
                }
            }
        }

        //??????????????????????????????????????????????????????????????????????????????????????????????????????
        if(minjudge == 0 && maxjudge == 0){
            rangeAdjust = 1;
        }

        //????????????????????????????????????????????????????????? ??????????????????????????????????????????????????????????????????????????? ?????????????????????????????????????????????

    }


    /**
     * ??????4???????????????????????????????????????????????????
     */
    private void selectGain() {
        gainJudgmentTdr();
        switch (gainState) {
            case 0:
                //???????????????????????????????????????
                tvInformation.setText("");
                break;
            case 1:
                tvInformation.setText("");
                gainState = 0;
                gain = gain - 1;
                setGain(gain);
                handler.postDelayed(ModeActivity.this::clickTest, 100);
                return;
            case 2:
                tvInformation.setText("");
                gainState = 0;
                gain = gain + 1;
                setGain(gain);
                handler.postDelayed(ModeActivity.this::clickTest, 100);
                return;
            default:
                break;
        }
        tdrCurveFitting();
        tdtAutoCursor();

        //??????????????????
        isLongClick = false;
        longTestInit = false;
        balanceIsReady = false;
        rangeIsReady = false;

    }

    /**
     * ??????????????????????????????????????????????????????????????????
     */
    int b_pos = 0;
    int b1_pos = 0;
    int b2_pos = 0;
    private void findStartExtremePoint(){
        //??????????????????
        int a;
        int b;
        //int j = 34;
        int j = 3;
        int maxNum = 0;
        int[] maxData = new int[65560];
        int[] maxDataPos = new int[65560];
        int max = maxData[0];
        int maxPos = maxDataPos[0];
        //???????????????
        //while ( (j >= 34) && (j < dataMax - removeTdrSim[rangeState]) ) {
        while ( (j >= 3) && (j < 100) ) {
            if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                if (waveArray[j - 1] >= waveArray[j - 2]) {
                    if (waveArray[j - 2] > waveArray[j - 3]) {
                        maxData[maxNum] = waveArray[j];
                        maxDataPos[maxNum] = j;
//                            Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                        maxNum++;
                    }
                }
            }
            j++;
        }
        if (maxNum == 0) {
            Log.e("tdr", "?????????????????????");
//            tvInformation.setVisibility(View.VISIBLE);
//            tvInformation.setText(getResources().getString(R.string.testAgain));
        }else {
            for (int k = 0; k < maxNum; k++) {
                if (maxData[k] >= max) {
                    max = maxData[k];
                    maxPos = maxDataPos[k];
                }
            }

        }
       // a = Math.abs(max - 132);
        a = Math.abs(max - Median_value); //jk20210520
        b1_pos = maxPos;

        int i1 = 34 ;
        int minNum1 = 0;
        int[] minData1 = new int[65560];
        int[] minDataPos1 = new int[65560];
        int minPos=minDataPos1[0];
        int min1 = waveArray[0];
        //????????????????????????
        while ( (i1 >= 5 ) && (i1 < 100) ) {   //jk20200714
            if ((waveArray[i1] < waveArray[i1 - 1]) && (waveArray[i1] <= waveArray[i1 + 1])) {
                if (waveArray[i1 - 1] <= waveArray[i1 - 2]) {
                    if (waveArray[i1 - 2] <= waveArray[i1 - 3]) {
                        if (waveArray[i1 - 3] <= waveArray[i1 - 4]) {
                            if (waveArray[i1 - 4] <= waveArray[i1 - 5]) {
                                minData1[minNum1] = waveArray[i1];
                                minDataPos1[minNum1] = i1;
                                minNum1++;
                                // Log.e("ceshi", " /??????????????? = " + i1);
                            }
                        }
                    }
                }
            }
            i1++;
        }
        if (minNum1 > 0) {
            for (int k1 = 0; k1 < minNum1; k1++) {
                if (minData1[k1] <= min1) {
                    min1 = minData1[k1];
                    minPos = minDataPos1[k1];
                }
            }
        }
       // b = Math.abs(132 - min1);
        b = Math.abs(Median_value - min1); //jk20210520
        b2_pos = minPos;
        // Log.e("a", " /?????? " +a);
        //Log.e("b", " /?????? " +b);
        // Log.e("min1", " /zhi " +min1);
        // Log.e("minpos", " /zhi " + minPos);
        if(a < b && min1 <= 126 ){       //jk20200714
            b_pos = b2_pos;
            //  Log.e("1", " /???????????? " );
        }else{
            b_pos = b1_pos;
            //  Log.e("2", " /???????????? " );
        }

    }

    /**
     * ????????????????????????????????????
     */
    int sum_num;
    void  balanceAutoTdr(){
        int temp1 = 0;
        int temp2 = 0;
        int j ;

        if (b_pos <= 50){
            if (b_pos >= 21) {
                j = b_pos - 21;
            } else {
                j = 0;
            }
        } else {
            j = 34;
        }

        for(int k = 54; k < 60; k++){
            sum_num = sum_num + waveArray[k];
        }
        sum_num = sum_num / 6;

        for (int i = 0; i <= j; i++) {
          /*  if ( waveArray[i] < 132) {    //???128
                temp1 = temp1 + (132 - waveArray[i]);
            } else {
                temp2 = temp2 + (waveArray[i] - 132);
            }*/
            if ( waveArray[i] < Median_value) {    //???128 //jk20210520
                temp1 = temp1 + (Median_value - waveArray[i]);
            } else {
                temp2 = temp2 + (waveArray[i] - Median_value);
            }
        }

        if ((temp1 > temp2) && ((temp1 - temp2) > 5)) {
            balanceState = 1;
            return;
        }
        /* ?????????????????? */
        if ((temp2 > temp1) && ((temp2 - temp1) > 5)) {
            balanceState = 2;
        }

    }

    /**
     * ????????????????????????????????????  //jk20200711
     */
    private void gainJudgmentTdr() {
        int i;
        int max = 0;
        int sub;

        //?????????????????????????????????
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
           // sub = waveArray[i] - 133;
            sub = waveArray[i] - Median_value; //jk20210520
            if (Math.abs(sub) > max) {
                max = Math.abs(sub);
            }
        }
        if (max <= 40) {//if (max <= 45) {     //jk20200824
            gainState = 2;
            return;
        }
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
            if ((waveArray[i] > 242) || (waveArray[i] < 20)) {
                //??????????????????
                gainState = 1;
                return;
            }
        }

    }

    private void gainJudgmentTdr1() {
        int i;
        int max = 0;
        int sub;

        //?????????????????????????????????
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
          //  sub = waveArray[i] - 133;
            sub = waveArray[i] - Median_value; //jk20210520
            if (Math.abs(sub) > max) {
                max = Math.abs(sub);
            }
        }
        if (max <= 25) {//if (max <= 45) {
            gainState = 2;
            return;
        }
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
            if ((waveArray[i] > 242) || (waveArray[i] < 20)) {
                //??????????????????
                gainState = 1;
                return;
            }
        }

    }

    /**
     * ????????????????????????????????????   //jk20200714
     */
    private void findExtremePoint(){
        //??????????????????
        int a;
        int b;
        int t1;
        int j = pulsetdrRemove1[rangeState] + 3;
        int maxNum = 0;
        int[] maxData = new int[65560];
        int[] maxDataPos = new int[65560];
        int max = maxData[0];
        int maxPos = maxDataPos[0];
        //?????????????????????????????????????????????????????????????????????
       while ( (j >= pulsetdrRemove1[rangeState] + 3) && (j < dataMax - removeTdrSim[rangeState]) ) {
       // while ( (j >= pulsetdrRemove1[rangeState]-2 ) && (j < dataMax - removeTdrSim[rangeState]) ) {
            if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                if (waveArray[j - 1] >= waveArray[j - 2]) {
                    if (waveArray[j - 2] >= waveArray[j - 3]) {
                        maxData[maxNum] = waveArray[j];
                        maxDataPos[maxNum] = j;
//                            Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                        maxNum++;
                    }
                }
            }
            j++;
        }

        if (maxNum == 0) {
             Log.e("tdr", "???????????????????????????");
            //tvInformation.setVisibility(View.VISIBLE);
           // tvInformation.setText(getResources().getString(R.string.testAgain));
        } else {
            for (int k = 0; k < maxNum; k++) {
                if (maxData[k] >= max) {
                    max = maxData[k];
                    maxPos = maxDataPos[k];
                }
            }

        }
        //a=Math.abs(max-132);
        a=Math.abs(max-Median_value);   //jk20210520

        int t2;
        int i1 = pulsetdrRemove1[rangeState]+5 ;
        int minNum1 = 0;
        int[] minData1 = new int[65560];
        int[] minDataPos1 = new int[65560];
        int minPos=minDataPos1[0];
        int min1 = waveArray[0];

        while ( (i1 >= pulsetdrRemove1[rangeState]+5 ) && (i1 < dataMax - removeTdrSim[rangeState]) ) {   //jk20200714
            if ((waveArray[i1] < waveArray[i1 - 1]) && (waveArray[i1] <= waveArray[i1 + 1])) {
                if (waveArray[i1 - 1] <= waveArray[i1 - 2]) {
                    if (waveArray[i1 - 2] <= waveArray[i1 - 3]) {
                        if (waveArray[i1 - 3] <= waveArray[i1 - 4]) {
                            if (waveArray[i1 - 4] <= waveArray[i1 - 5]) {
                                minData1[minNum1] = waveArray[i1];
                                minDataPos1[minNum1] = i1;
                                minNum1++;
                                // Log.e("ceshi", " /??????????????? = " + i1);
                            }
                        }
                    }
                }
            }
            i1++;
        }

        if (minNum1 > 0) {
            for (int k1 = 0; k1 < minNum1; k1++) {
                if (minData1[k1] <= min1) {
                    min1 = minData1[k1];
                    minPos = minDataPos1[k1];
                }
            }
        }

        //b=Math.abs(132-min1);
        b=Math.abs(Median_value-min1);  //jk20210520
         Log.e("a", " /?????? " +a);
        Log.e("b", " /?????? " +b);
         Log.e("min1", " /zhi " +min1);
        // Log.e("minpos", " /zhi " + minPos);
      /*  if(a<=b && min1 <=105 ){       //jk20200714
            point_x();
            // b_pos=b2_pos;
            //  Log.e("1", " /???????????? " );
        }else{
            point_s();
            // b_pos=b1_pos;
            //  Log.e("2", " /???????????? " );
        }*/

        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????

        //????????????????????????
        int secondMax = 0;
        int secondmaxPos = 0;
        if (maxNum == 0) {
            Log.e("tdr", "???????????????????????????");
        } else {
            for (int k = 0; k < maxNum; k++) {
                if ((maxData[k] < max) && (maxData[k] >= secondMax)) {
                    secondMax = maxData[k];
                    secondmaxPos = maxDataPos[k];
                }
            }
        }

        int secondMin = 128;
        int secondminPos = 0;
        if (minNum1 > 0) {
            for (int k1 = 0; k1 < minNum1; k1++) {
                if ((minData1[k1] > min1) && (minData1[k1] <= secondMin)) {
                    secondMin = minData1[k1];
                    secondminPos = minDataPos1[k1];
                }
            }
        }


        secondMAx = secondMax - Median_value;  //jk20210531
        secondMIn = secondMin - Median_value;
        secondMAxPos = secondmaxPos;
        secondMInPos = secondminPos;


        //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????  //jk20210310


        int flaggo = 0;
        if((minPos < pulsetdrRemove11[rangeState]) && (maxPos > 2*pulsetdrRemove11[rangeState]) && (max >=Median_value + 8)){
            point_s();
            flaggo = 1;
        }

        if(flaggo == 0) {
            if ((minPos > 2 * pulsetdrRemove11[rangeState]) && (maxPos < pulsetdrRemove11[rangeState])&&(min1 <=Median_value - 8)) {
                //???????????????????????????????????????????????????????????????????????????????????????
                point_x();
            } else {
                if (a < b) {
                    point_x();
                } else {
                    point_s();
                }
            }

        }


    }

    /**
     * ????????????????????????  //jk20200711
     */
    int pointflag = 0;
    private void point_s(){
        //??????????????????
        int t1;
        int j = pulsetdrRemove[rangeState] + 3;
        int maxNum = 0;
        int[] maxData = new int[65560];
        int[] maxDataPos = new int[65560];
        int max = maxData[0];
        int maxPos = maxDataPos[0];
        //?????????????????????????????????????????????????????????????????????
        while ( (j >= pulsetdrRemove[rangeState] + 3) && (j < dataMax - removeTdrSim[rangeState]) ) {
            if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                if (waveArray[j - 1] >= waveArray[j - 2]) {
                    if (waveArray[j - 2] >= waveArray[j - 3]) {
                        maxData[maxNum] = waveArray[j];
                        maxDataPos[maxNum] = j;
//                            Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                        maxNum++;
                    }
                }
            }
            j++;
        }

        if (maxNum == 0) {
          Log.e("tdr", "???????????????");
           // tvInformation.setVisibility(View.VISIBLE);
          //  tvInformation.setText(getResources().getString(R.string.testAgain));
        }else {
            for (int k = 0; k < maxNum; k++) {
                if (maxData[k] >= max) {
                    max = maxData[k];
                    maxPos = maxDataPos[k];
                }
            }
           // Log.e("1", " /????????????????????? = " + maxPos);
        }
        g = maxPos;
        pointflag = 1;
        //b1_pos= maxPos;
        Log.e("2", " /????????????????????? = " + g);


        t1 = maxPos;//min1Pos;
        //???????????????
        while (t1 > 1) {
           if (waveArray[t1] >= waveArray[t1-1]) {
           // if (waveArray[t1] != waveArray[t1-2]) {
               if (Math.abs(waveArray[t1] - Median_value) > 4) {
                t1 = t1 - 1;
           //
           }else{
                break;
            }
            } else {
                break;
            }
        }
        u = t1;
        Log.e("3", " /????????? = " + u);

        }

    /**
     * ????????????????????????  //jk20200711
     */
    private void point_x() {
        int t2;
        int i1 = pulsetdrRemove1[rangeState]+5;//  int i1 = pulsetdrRemove[rangeState] + 5;   //jk20200714 ??????5???
        int minNum1 = 0;
        int[] minData1 = new int[65560];
        int[] minDataPos1 = new int[65560];
        int minPos=minDataPos1[0];

        while ( (i1 >= pulsetdrRemove1[rangeState]+5 ) && (i1 < dataMax - removeTdrSim[rangeState]) ) {   //jk20200714  ??????5???
            if ((waveArray[i1] < waveArray[i1 - 1]) && (waveArray[i1] <= waveArray[i1 + 1])) {
                if (waveArray[i1 - 1] <= waveArray[i1 - 2]) {
                    if (waveArray[i1 - 2] <= waveArray[i1 - 3]) {
                        if (waveArray[i1 - 3] <= waveArray[i1 - 4]) {
                            if (waveArray[i1 - 4] <= waveArray[i1 - 5]) {
                                minData1[minNum1] = waveArray[i1];
                                minDataPos1[minNum1] = i1;
                                minNum1++;
                                //Log.e("ceshi", " /??????????????? = " + i1);
                            }
                        }
                    }
                }
            }
            i1++;
        }

        if (minNum1 > 0) {
            int min1 = minData1[0];
            for (int k1 = 0; k1 < minNum1; k1++) {
                if (minData1[k1] <= min1) {
                    min1 = minData1[k1];
                    minPos = minDataPos1[k1];
                }
            }

        }

    t2 = minPos;
  //  b2_pos=minPos;
    g = minPos;
    pointflag = 2;
        // Log.e("2", " /????????????????????? = " + g);
        //???????????????
        while (t2 > 1) {
            //if (waveArray[t2] <= waveArray[t2-1]) {
            if (waveArray[t2] <= waveArray[t2-1]) {
               // if (waveArray[t2] != waveArray[t2-2]) {
                if (Math.abs(waveArray[t2] - Median_value) > 4) {
                    t2 = t2 - 1;
                }else{
                    break;
                }
            } else {
                break;
            }
        }
        u = t2;
     //   Log.e("3", " /?????????????????? = " + u);

    }

    /**
     * ????????????????????????????????????  //GC20190708
     */
    private void icmAutoTest() {
        //1.????????????????????????
        gainJudgment();
        switch (gainState) {
            case 0:
                tvInformation.setText("");
                break;
            case 1:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_high));
                return;
            case 2:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_low));
                return;
            default:
                break;
        }
        //????????????
        softwareFilter();
        //??????
        integral();
        //2.??????????????????
        breakdownJudgment();
        //??????????????? //GC20191231
        if (!breakDown) {
            //???????????????    //GC20190710
            tvInformation.setVisibility(View.VISIBLE);
            tvInformation.setText(getResources().getString(R.string.not_break_down));
            return;
        }
        //??????????????????
        calculatePulse();
        //????????????????????????????????????
        correlationSimple();
        //?????????????????????????????????????????????
        breakPointCalculate();
        //??????????????????
        icmAutoCursor();

    }

    /**
     * ??????????????????????????????????????????  //GC20200109
     */
    private void icmAutoTestDC() {
        //1.????????????????????????
        gainJudgment();
        switch (gainState) {
            case 0:
                tvInformation.setText("");
                break;
            case 1:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_high));
                return;
            case 2:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_low));
                return;
            default:
                break;
        }
        //????????????
        softwareFilter();
        //??????????????????
        calculatePulse();
        //????????????????????????????????????
        correlationSimpleDC();
        //??????????????????
        icmAutoCursor();

    }

    /**
     * ????????????????????????????????????
     */
    private void gainJudgment() {
        int i;
        int max = 0;
        int sub;

        //???ICM??????????????????????????????    //GC20200606
        int sum = 0;
        for (int j = 0; j < 10; j++) {
            sum += Constant.WaveData[j];
        }
        int ave = sum / 10;

        //?????????????????????????????????
        for (i = 0; i < dataMax - removeIcmDecay[rangeState]; i++) {
            sub = Constant.WaveData[i] - ave;
            if (Math.abs(sub) > max) {
                max = Math.abs(sub);
            }
        }
        if (max <= 25) {// if (max <= 42) {
            //????????????????????????????????????????????? 15% 38
            gainState = 2;
            return;
        }
        for (i = 0; i < dataMax - removeIcmDecay[rangeState]; i++) {
            if ((Constant.WaveData[i] > 242) || (Constant.WaveData[i] < 25)) {
//            if ((waveArray[i] > 242) || (waveArray[i] < 13)) {    //A20200527  ICM????????????????????????
                //??????????????????
                gainState = 1;
                return;
            }
        }
    }

    /**
     * ??????????????????????????????   ???????????????????????????-?????????????????????????????????????????????????????????750kHz????????????????????????????????????????????????
     */
    private void softwareFilter() {
        //???ICM??????????????????????????????  //GC20200606
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Constant.WaveData[i];
        }
        int ave = sum / 10;
        for (int i = 1; i < dataMax - removeIcmDecay[rangeState]; i++) {
            if (rangeState > 6) {
                //25M????????????32km???64km??????
                waveArrayFilter[i] = 0.6232 * waveArrayFilter[i - 1] + 0.3768 * (double) (Constant.WaveData[i] - ave);//1.5M
            } else {
                waveArrayFilter[i] = 0.9058 * waveArrayFilter[i - 1] + 0.0942 * (double) (Constant.WaveData[i] - ave);//1.5M
            }
        }
    }

    /**
     * ??????????????????????????????   ???????????????????????????-????????????,????????????
     */
    private void integral() {
        for (int i = 1; i < dataMax - removeIcmDecay[rangeState]; i++) {
            if (rangeState > 6) {
                //25M????????????32km???64km??????
                waveArrayIntegral[i] = waveArrayIntegral[i - 1] + waveArrayFilter[i] * 4e-8;
            } else {
                waveArrayIntegral[i] = waveArrayIntegral[i - 1] + waveArrayFilter[i] * 1e-8;
            }
        }
    }

    /**
     * ????????????????????????
     */
    private void breakdownJudgment() {
        int i;
        //??????????????????????????????(?????????????????????????????????)
        int start = 140;    //GC20200110 ??????????????? start = 92;
        double sum = 0;
        int a = -1;
        double min = 0;
        for (i = start + 64; i < dataMax - 50; i++) {
            if (waveArrayIntegral[i] < min) {
                min = waveArrayIntegral[i];
                a = i;
            }
        }
        //GC20191231    //?????????  ??????????????????
        breakDown = false;
        //?????????????????????
//        for (i = start + 64; i < start + 72; i++) {
        for (i = start + 174; i < start + 182; i++) {
            sum = sum + waveArrayIntegral[i];
        }
        sum = sum / (double)8;
        Log.e("????????????",   " /min = " + min + " /???????????? = " + a + " /sum*1.3 = " + sum * 1.3 + " /sum*1.2 = "  + sum * 1.2);
        //????????????
        for (i = start + 64; i < dataMax - 50; i++) {
            if (waveArrayIntegral[i] < 0) {
                if(waveArrayIntegral[i] < sum * (double)1.3) {
                    breakDown = true;
                    break;
                }
            }
        }

    }

    /**
     * ??????????????????  ??????????????????   ???????????????????????????-?????????????????????????????????VL=L * di/dt??????????????????*????????? //?????????????????????????????????
     */
    double L = 10e-6;
    double z = 25;
    private void calculatePulse() {
        double[] V = new double[65560];
        int i;
        double min = 0;
        //????????????
        int breakPoint = 0;
        int start;

        //????????????  //GC20200103
        Log.e("leadCat", "leadCat" +MainActivity.leadCat);
        if ((leadLength > 0) && (MainActivity.leadCat == 1)) {  //if (leadLength > 0) {// if ((leadLength > 0) && (leadCat == 1)) {  //jk20201130  ???????????????????????????????????????
            z = (double)50;
        } else {
            z = (double)25;
        }
        //GC20191231
        for (i = 0; i < dataMax - removeIcmDecay[rangeState] - 1; i++) {
            if (rangeState > 6) {
                //25M????????????32km???64km??????
                V[i] = (waveArrayFilter[i + 1] - waveArrayFilter[i]) * 4.0e8;
            } else {
                V[i] = (waveArrayFilter[i + 1] - waveArrayFilter[i]) * 1.0e8;
            }
        }
        //?????????  ??????????????????
        start = 140;    //GC20200110 ??????????????? start = 120
        //?????????????????????????????????????????????????????????64???????????????????????????????????????
        for (i = start + 64; i < dataMax - 50; i++) {
            if((V[i] < min) && (V[i] < 0)) {
                min = V[i];
                breakPoint = i;
            }
        }
        breakBk = breakPoint;

        //??????VL
        for(i = 0; i < dataMax - removeIcmDecay[rangeState]; i++) {
            V[i] = V[i] * L * -1.0;
        }
        //??????????????????
        for(i = 0; i < dataMax - removeIcmDecay[rangeState]; i++) {
            s1[i] = V[i] + waveArrayFilter[i] * z;
            s2[i] = V[i] - waveArrayFilter[i] * z;
        }

    }

    /**
     * ????????????????????????  ??????????????????(?????????????????????)  ???????????????????????????-??????????????????????????????
     */
    double false_flag = 0 ;
    private void correlationSimpleDC()  {
        int i;
        int j = 0;
        int k;
        float p;
        float[] P = new float[510];
        int w1;
        int w2;
        int w3;
        float[] s1Simple = new float[510];
        float[] s2Simple = new float[510];
        int maxBak ;

        //GC20200109 DC???????????????
        breakBk = 140;
        if(range >= 6) {//25M??????
            if(breakBk > (50/4)) {//???????????????32km???64km???????????????????????????????????????
                w1 = breakBk - (50/4);      //???????????????
            } else {
                w1 = breakBk;
            }
            w2 = breakBk + (350/4);     //???????????????
        } else {
            if(breakBk > 50) {//???????????????32km???64km???????????????????????????????????????
                w1 = breakBk - 50;      //???????????????
            } else {
                w1 = breakBk;
            }
            w2 = breakBk + 350;     //???????????????
        }
        for(i = 0;i < 510;i++) { //??????
            s1Simple[i] = (float)s1[j];
            s2Simple[i] = (float)s2[j];
            j = j + densityMaxIcmDecay[rangeState];
        }
        w1 = w1 / densityMaxIcmDecay[rangeState];
        w2 = w2 / densityMaxIcmDecay[rangeState];
        w3 = 510 - w2;

        float[] S1 = new float[65556];
        float[] S2 = new float[65556];

        if(w2 >= 510){  //jk20210420
            w2 = 510;
            false_flag = 1;
        }
        if(w1 >= 510){  //jk20210420
            w1 = 510;
            false_flag = 1;
        }
        if(w3 >= 510){  //jk20210420
            w3 = 510;
            false_flag = 1;
        }

        for(i = w1;i < w2;i++) {
            S1[i - w1] = s1Simple[i];
        }
        for(i = 0;i < w3;i++) {
            for(k = w1;k < w2;k++) {
                S2[k - w1] = s2Simple[k + i];
            }
            p = (float)0.0;                    //??????
            for(j = 0;j < (w2 - w1);j++) { //??????????????????
                p += S1[j] * S2[j] * -1.0;
            }
            P[i] = p;                //???????????????????????????????????????P?????????
        }
        //??????P???????????????????????????????????????
        float max = P[0];
        int maxIndex = 0;
        for (i = 0; i < w3; i++) {
            if (P[i] > max) {
                max = P[i];
                maxIndex = i;
            }
        }

        //???????????????????????????????????????
        maxIndex = (w1 + maxIndex) * densityMaxIcmDecay[rangeState];
        //GC20191231
        maxBak = maxIndex;

        w1 = w1 * densityMaxIcmDecay[rangeState];
        w2 = w2 * densityMaxIcmDecay[rangeState];

        for (i = w1; i < w2; i++) {
            S1[i - w1] = (float)s1[i];
        }

        for (i = (maxIndex - densityMaxIcmDecay[rangeState]); i < (maxIndex + densityMaxIcmDecay[rangeState]); i++) {
            for (k = 0; k < w2 - w1; k++) {
                S2[k] = (float)s2[k + i];
            }
            //??????
            p = (float)0.0;
            //??????????????????S
            for (j = 0; j < (w2 - w1); j++) {
                p += S1[j] * S2[j] * -1.0;
            }
            //???????????????????????????????????????P?????????
            P[i - (maxIndex - densityMaxIcmDecay[rangeState])] = p;
        }
        max = P[0];
        int maxIndex1 = 0;
        for (i = 0; i < densityMaxIcmDecay[rangeState] * 2; i++) {
            if (P[i] > max) {
                max = P[i];
                maxIndex1 = i;
            }
        }
        maxIndex = maxIndex - densityMaxIcmDecay[rangeState] + maxIndex1 - w1;

        //GC20191231
        if(maxIndex <= 0) {
            maxIndex = maxBak;
        }
        faultResult = maxIndex;
        //GN ????????????????????????????????????????????????
        calculateDistanceAuto(maxIndex);
    }

    /**
     * ??????????????????  ??????????????????(?????????????????????)  ???????????????????????????-??????????????????????????????        //GC20191231
     */
    private void correlationSimple() {
        int i;
        int j = 0;
        int k;
        double p;
        double[] P = new double[510];
        int w1;
        int w2;
        int w3;
        double[] s1Simple = new double[510];
        double[] s2Simple = new double[510];
        int maxBak ;
        double distance;

        if (rangeState > 6) {
            //25M????????????32km???64km??????
            if (breakBk > (50 / 4)) {
                //???????????????
                w1 = breakBk - (50 / 4);
            } else {
                w1 = breakBk;
            }
            //???????????????
            w2 = breakBk + (350 / 4);
        } else {
            if (breakBk > 50) {
                //???????????????
                w1 = breakBk - 50;
            } else {
                w1 = breakBk;
            }
            //???????????????
            w2 = breakBk + 350;
        }

        //??????
        for (i = 0; i < 510; i++) {
            s1Simple[i] = s1[j];
            s2Simple[i] = s2[j];
            j = j + densityMaxIcmDecay[rangeState];
        }
        w1 = w1 / densityMaxIcmDecay[rangeState];
        w2 = w2 / densityMaxIcmDecay[rangeState];
        w3 = 510 - w2;

        double[] S1 = new double[65556];
        double[] S2 = new double[65556];

        if(w2 >= 510){  //jk20210420
            w2 = 510;
            false_flag = 1;
        }
        if(w1 >= 510){  //jk20210420
            w1 = 510;
            false_flag = 1;
        }
        if(w3 >= 510){  //jk20210420
            w3 = 510;
            false_flag = 1;
        }

        for (i = w1; i < w2; i++) {
            S1[i - w1] = s1Simple[i];
        }
        for (i = 0; i < w3; i++) {
            for (k = w1; k < w2; k++) {
                S2[k - w1] = s2Simple[k + i];
            }
            p = 0.0;
            //??????????????????
            for (j = 0; j < (w2 - w1); j++) {
                p += S1[j] * S2[j] * -1.0;
            }
            //???????????????????????????????????????P?????????
            P[i] = p;
        }

        //??????P???????????????????????????????????????
        double max = P[0];
        int maxIndex = 0;
        for (i = 0; i < w3; i++) {
            if (P[i] > max) {
                max = P[i];
                maxIndex = i;
            }
        }

        //???????????????????????????????????????
        maxIndex = (w1 + maxIndex) * densityMaxIcmDecay[rangeState];
        //GC20191231
        maxBak = maxIndex;

        //?????????????????????0??????????????????    ????????????????????????
        if(maxIndex == 0) {
            double[] maxData = new double[65560];
            double[] maxData1 = new double[65560];
            int[] maxDataPos  = new int[65560];
            //????????????
            double a = 0.05;
            i = 3;
            j = 1;
            maxDataPos[0] = 0;
            maxData[0] = P[0];
            while ((j < 255) && (i <w3)) {
                if ((P[i] > P[i - 1]) && (P[i] >= P[i + 1])) {
                    if((i >= 3) && (P[i - 1] > P[i - 2])) {
                        if(P[i - 2] > P[i - 3]) {
                            maxDataPos[j] = i;
                            maxData[j] = P[i];
                            j++;
                        }
                    }
                }
                i++;
            }
            k = 0;
            for(i = 0;i < j;i++) {
                //????????????>0.3?????????????????????
                if(maxData[i] > 0.3 * max) {
                    //max_data[i]????????????max_data_pos[i]
                    distance = pointToDistance((int) maxData[i]);
                    //a?????? 20190821
                    maxData1[k] = maxData[i] / ((double)1-((double)2 * a * (distance/(double)1000)));
                    maxDataPos[k] = maxDataPos[i];
                    k++;
                }
            }
            //????????????
            sort(maxData1,maxDataPos,k);
            if (pointToDistance(maxDataPos[0]) >= 10) {
                //???????????????10??????
                maxIndex = maxDataPos[0];
            } else if ((pointToDistance(maxDataPos[1]) < 10) && (maxData1[1] < maxData1[0] * 0.4))  {
                maxIndex = maxDataPos[0];
            } else if ((pointToDistance(maxDataPos[1]) >= 80) && (maxData1[1] >= maxData1[0] * 0.4)) {
                maxIndex = maxDataPos[1];
            } else if ((pointToDistance(maxDataPos[1]) >= 10) && (pointToDistance(maxDataPos[1]) >= 80)) {
                if (maxData1[2] >= maxData1[0] * 0.4) {
                    if(Math.abs(pointToDistance(maxDataPos[2]) - (double)2 * (pointToDistance(maxDataPos[1]) + (double)10)) < (double)10) {
                        maxIndex = maxDataPos[1];
                    }
                }
            } else if (pointToDistance(maxDataPos[2]) >= 80) {
                maxIndex = maxDataPos[2];
            } else if (((pointToDistance(maxDataPos[2]) >= 10) && (pointToDistance(maxDataPos[2]) >= 80))) {
                if(maxData1[2] >= maxData1[0] * 0.4) {
                    if(Math.abs(pointToDistance(maxDataPos[3]) - (double)2 * (pointToDistance(maxDataPos[2]) + (double)10)) < (double)10) {
                        maxIndex = maxDataPos[2];
                    }
                } else {
                    maxIndex = maxDataPos[0];
                }
            }
        }

        w1 = w1 * densityMaxIcmDecay[rangeState];
        w2 = w2 * densityMaxIcmDecay[rangeState];

        for (i = w1; i < w2; i++) {
            S1[i - w1] = s1[i];
        }

        for (i = (maxIndex - densityMaxIcmDecay[rangeState]); i < (maxIndex + densityMaxIcmDecay[rangeState]); i++) {
            for (k = 0; k < w2 - w1; k++) {
                S2[k] = s2[k + i];
            }
            //??????
            p = 0.0;
            //??????????????????S
            for (j = 0; j < (w2 - w1); j++) {
                p += S1[j] * S2[j] * -1.0;
            }
            //???????????????????????????????????????P?????????
            P[i - (maxIndex - densityMaxIcmDecay[rangeState])] = p;
        }
        max = P[0];
        int maxIndex1 = 0;
        for (i = 0; i < densityMaxIcmDecay[rangeState] * 2; i++) {
            if (P[i] > max) {
                max = P[i];
                maxIndex1 = i;
            }
        }
        maxIndex = maxIndex - densityMaxIcmDecay[rangeState] + maxIndex1 - w1;

        //GC20191231
        if(maxIndex <= 0) {
            maxIndex = maxBak;
        }
        faultResult = maxIndex;
        //GN ????????????????????????????????????????????????
        calculateDistanceAuto(maxIndex);
    }

    /**
     * @param samplingPoints
     */
    int pointToDistance(int samplingPoints) {
        int Fx1;
        int k = 1;
        //?????????????????????range=6(32km)???range=7(64km)??????25M???????????????????????????????????????100M??????????????????????????????????????????????????????4???
        if (rangeState > 6){
            k = 4;
        }
        //???????????????????????????
        Fx1 = (int) (((samplingPoints * velocity) * k) / 200);
        return(Fx1);

    }

    /**
     * @param samplingPoints ???????????????????????????-??????????????????   //GC20191231 ??????????????????????????????????????????
     */
    private void calculateDistanceAuto(int samplingPoints) {
        int k = 1;
        int l;
        int lFault;
        double distance;

        //?????????????????????range=6(32km)???range=7(64km)??????25M???????????????????????????????????????100M??????????????????????????????????????????????????????4???
        if (rangeState > 6){
            k = 4;
        }
        //????????????  //GC20200103
        Log.e("leadCat", "leadCat" +MainActivity.leadCat);
        if ((leadLength > 0) && (MainActivity.leadCat == 1)) {  //jk20201130  ???????????????????????????????????????// if(leadLength > 0) {
            //????????????
            l = (int) (leadLength * 2000 / leadVop / 10);
            lFault = samplingPoints - l;
            distance = (((double) lFault * velocity) * k) / 2 * 0.01 + leadLength;
        } else {
            distance = (((double) samplingPoints * velocity) * k) / 2 * 0.01;
        }
        //????????????????????????
//        tvAutoDistance.setText(new DecimalFormat("0.00").format(distance) + "m");
        //??????????????????
//        tvDistance.setText(new DecimalFormat("0.00").format(distance) + "m");

    }

    /**
     * ?????????????????????????????????  //GC20191231
     */
    private void sort(double[] a,int[] b,int l) {
        int i, j;
        double v;
        int k;
        //????????????
        for(i = 0; i < l - 1; i ++) {
            for(j = i+1; j < l; j ++) {
                //??????????????????????????????????????????
                if(a[i] < a[j]) {
                    v = a[i];
                    a[i] = a[j];
                    a[j] = v;
                    k = b[i];
                    b[i] = b[j];
                    a[j] = k;
                }
            }
        }
    }

    /**
     * ?????????????????????,????????????????????????
     */
    private void breakPointCalculate() {
        int i;
        int j;
        int k;
        int start;
        double min = 0;
        double[] Diff = new double[65560];
        int pos = 0;

        double p;
        double[] P = new double[65560];
        int w1;
        int w2;
        int w3;

        double[] D1 = new double[65560];
        double[] D2 = new double[65560];
        double[] maxData = new double[65560];
        int[] maxDataPos = new int[65560];

        start = 140;    //GC20200110  start = 0;
        for (i = 0; i < (dataMax - 50); i++) {
            Diff[i] =  (waveArrayFilter[i + 1] - waveArrayFilter[i]);
        }

        for (i = start + faultResult; i < (dataMax - 50); i++) {
            if ((Diff[i] < min) && (Diff[i] < 0)) {
                min = Diff[i];
                //pos???????????????????????????+????????????
                pos = i - (start + faultResult);
            }
        }
        pos = pos + (start + faultResult);

        w1 = pos - 30;
        w2 = pos + 70;
        w3 = pos - (start + faultResult);
        for(i = w1; i < w2; i++) {
            D1[i - w1] = waveArrayFilter[i];
        }


        for (i = (start + faultResult); i < pos; i++) {
            for(k = i; k < (i + 100); k++) {
                D2[k - i] = waveArrayFilter[k];
            }
            p = 0.0;
            for(j = 0;j < (w2 - w1);j++) {
                //??????????????????
                p += D1[j] * D2[j];
            }
            //???????????????????????????????????????P?????????
            P[i - (start + faultResult)] = p;
        }

        //??????P???????????????????????????????????????
        double max = P[0];
        int maxIndex = 0;
        for (i = 0;i < w3;i++) {
            if(P[i] > max) {
                max = P[i];
                maxIndex = i;
            }
        }
        breakBk = maxIndex;
        //????????????????????????????????????>0.7?????????????????????????????????????????????
        i = 3;
        j = 0;
        while ((j < 255) && (i < w3)) {
            if((P[i] > P[i - 1]) && (P[i] >= P[i + 1])) {
                if((i >= 3) && (P[i - 1] > P[i - 2])) {
                    if(P[i - 2] > P[i - 3]) {
                        maxDataPos[j] = i;
                        maxData[j] = P[i];
                        j++;
                    }
                }
            }
            i++;
        }
        for (k = 0; k < j; k++) {
            if (maxData[k] > 0.7 * Math.abs(max)) {
                //???????????????
                breakBk = maxDataPos[k];
                break;
            }
        }
        //???????????????
        breakBk = breakBk + start + faultResult + 10;
    }

    /**
     * ???????????????????????????????????? //GC20190708
     */
    private void icmAutoCursor() {
        //GC20200106
        if(false_flag == 0) {  //jk20210420 ????????????????????????????????????????????????
            zero = breakBk;
            pointDistance = breakBk + faultResult;
            //positionReal = zero / densityMax;
            //positionVirtual = pointDistance / densityMax;
            // sc 20200109   ????????????
            density = getDensity();
            Log.e("cursor", "??????" + density);
            if (density == densityMax) {
                positionReal = zero / densityMax;
                positionVirtual = pointDistance / densityMax;
            }
            //?????????????????????
            if (zero >= (currentMoverPosition510 * dataLength / 510) && zero <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
                positionReal = (zero - (currentMoverPosition510 * dataLength / 510)) / density;
                mainWave.setScrubLineReal(positionReal);
            } else {
                mainWave.setScrubLineRealDisappear();
            }
            //?????????????????????
            if (pointDistance >= (currentMoverPosition510 * dataLength / 510) && pointDistance <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
                positionVirtual = (pointDistance - (currentMoverPosition510 * dataLength / 510)) / density;
                mainWave.setScrubLineVirtual(positionVirtual);
            } else {
                mainWave.setScrubLineVirtualDisappear();
            }

            //????????????
            //   mainWave.setScrubLineReal(positionReal);
            //  mainWave.setScrubLineVirtual(positionVirtual);
            //????????????
            calculateDistance(Math.abs(pointDistance - zero));
        }
    }


    /**
     * ?????????????????????????????????510????????????????????????waveDraw???waveCompare
     */
    private void organizeWaveData() {
        //?????????????????????510??????
        for (int i = 0, j = 0; j < 510; i = i + densityMax, j++) {
            //??????TDR???ICM???ICM_DECAY???DECAY???SIM???????????????????????????
            waveDraw[j] = Constant.WaveData[i];
            //??????SIM???????????????????????????
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
        //???????????????????????????????????????dataLength
        if ((mode == TDR) || (mode == SIM)) {
            dataLength = dataMax - removeTdrSim[rangeState];
            //250m????????????  //GC20191223
            if (range == RANGE_250) {
                int[] waveDraw250 = new int[256];
                int[] waveCompare250 = new int[256];
                int[] simDraw2501 = new int[256];
                int[] simDraw2502 = new int[256];
                int[] simDraw2503 = new int[256];
                int[] simDraw2504 = new int[256];
                int[] simDraw2505 = new int[256];
                int[] simDraw2506 = new int[256];
                int[] simDraw2507 = new int[256];
                int[] simDraw2508 = new int[256];
                //?????????256?????????????????????500m????????????????????????????????????????????????510?????????
                for (int i = 0, j = 0; i < 256; i++, j++) {
                    //??????TDR???SIM????????????????????????
                    waveDraw250[j] = waveDraw[i];
                    //??????SIM???????????????????????????
                    if (mode == SIM) {
                        waveCompare250[j] = waveCompare[i];
                        if (!isDatabase) {
                            simDraw2501[j] = simDraw1[i];
                            simDraw2502[j] = simDraw2[i];
                            simDraw2503[j] = simDraw3[i];
                            simDraw2504[j] = simDraw4[i];
                            simDraw2505[j] = simDraw5[i];
                            simDraw2506[j] = simDraw6[i];
                            simDraw2507[j] = simDraw7[i];
                            simDraw2508[j] = simDraw8[i];
                        }
                    }
                }
                //???????????????256???????????????????????????255??????
                for (int i = 0, j = 1; i < 255; i++, j += 2) {
                    //??????TDR???SIM????????????????????????
                    waveDraw[j] = waveDraw250[i] + (waveDraw250[i + 1] - waveDraw250[i]) / 2;
                    //??????SIM???????????????????????????
                    if (mode == SIM) {
                        waveCompare[j] = waveCompare250[i] + (waveCompare250[i + 1] - waveCompare250[i]) / 2;
                        if (!isDatabase) {
                            simDraw1[j] = simDraw2501[i] + (simDraw2501[i + 1] - simDraw2501[i]) / 2;
                            simDraw2[j] = simDraw2502[i] + (simDraw2502[i + 1] - simDraw2502[i]) / 2;
                            simDraw3[j] = simDraw2503[i] + (simDraw2503[i + 1] - simDraw2503[i]) / 2;
                            simDraw4[j] = simDraw2504[i] + (simDraw2504[i + 1] - simDraw2504[i]) / 2;
                            simDraw5[j] = simDraw2505[i] + (simDraw2505[i + 1] - simDraw2505[i]) / 2;
                            simDraw6[j] = simDraw2506[i] + (simDraw2506[i + 1] - simDraw2506[i]) / 2;
                            simDraw7[j] = simDraw2507[i] + (simDraw2507[i + 1] - simDraw2507[i]) / 2;
                            simDraw8[j] = simDraw2508[i] + (simDraw2508[i + 1] - simDraw2508[i]) / 2;
                        }
                    }
                }
                //?????????255????????????
                for (int i = 0, j = 0; j < 255; i++, j++) {
                    waveDraw[2 * j] = waveDraw250[i];
                    //??????SIM???????????????????????????
                    if (mode == SIM) {
                        waveCompare[2 * j] = waveCompare250[i];
                        if (!isDatabase) {
                            simDraw1[2 * j] = simDraw2501[i];
                            simDraw2[2 * j] = simDraw2502[i];
                            simDraw3[2 * j] = simDraw2503[i];
                            simDraw4[2 * j] = simDraw2504[i];
                            simDraw5[2 * j] = simDraw2505[i];
                            simDraw6[2 * j] = simDraw2506[i];
                            simDraw7[2 * j] = simDraw2507[i];
                            simDraw8[2 * j] = simDraw2508[i];
                        }
                    }
                }
            }
        } else if ((mode == ICM) || (mode == DECAY) || (mode == ICM_DECAY)) {
            dataLength = dataMax - removeIcmDecay[rangeState];
            //250m????????????
            if (range == RANGE_250) {
                dataLength = dataLength / 2;
            }
        }
        //?????????????????????
        setHorizontalMoveView();

    }

    /**
     * ????????????????????????
     */
    private void organizeZoomWaveData(int start) {
        //???????????????????????????????????????510??????
        for (int i = start, j = 0; j < 510; i = i + density, j++) {
            //??????TDR???ICM???ICM_DECAY???DECAY???SIM???????????????????????????
            if (i < Constant.WaveData.length) {
                waveDraw[j] = Constant.WaveData[i];
            }
            //??????SIM???????????????????????????
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
    }

    /**
     * ????????????????????????????????????????????????
     */
    private void organizeCompareWaveData(int start) {
        //???????????????????????????????????????510??????
        for (int i = start, j = 0; j < 510; i = i + density, j++) {
            //??????TDR???ICM???ICM_DECAY???DECAY
            waveCompareDraw[j] = waveCompare[i];
        }
        if ((mode == TDR) || (mode == SIM)) {
            //250m??????  //GC20191223
            if (range == RANGE_250) {
                int[] waveDraw250 = new int[256];
                //?????????256??????????????????????????????????????????
                for (int i = 0, j = 0; i < 256; i++, j++) {
                    waveDraw250[j] = waveCompareDraw[i];
                }
                //???????????????256???????????????????????????255??????
                for (int i = 0, j = 1; i < 255; i++, j += 2) {
                    waveCompareDraw[j] = waveDraw250[i] + (waveDraw250[i + 1] - waveDraw250[i]) / 2;
                }
                //?????????255????????????
                for (int i = 0, j = 0; j < 255; i++, j++) {
                    waveCompareDraw[2 * j] = waveDraw250[i];
                }
            }
        }

    }

    /**
     * ?????????????????????????????????????????????????????????510?????? //GC20200611
     */
    private void organizeClickZoomData() {
        //???????????????????????????   //GC20200611
        drawClickZoomCursor();
        //???????????????510??????
        for (int i = currentStart, j = 0; j < 510; i = i + density, j++) {
            //??????TDR???ICM???ICM_DECAY???DECAY???SIM???????????????????????????
            waveDraw[j] = Constant.WaveData[i + index];
            //??????SIM???????????????????????????
            if (mode == SIM) {
                waveCompare[j] = Constant.SimData[i];
                if (!isDatabase) {
                    simDraw1[j] = simArray1[i];
                    simDraw2[j] = simArray2[i];
                    simDraw3[j] = simArray3[i];
                    simDraw4[j] = simArray4[i];
                    simDraw5[j] = simArray5[i];
                    simDraw6[j] = simArray6[i];
                    simDraw7[j] = simArray7[i];
                    simDraw8[j] = simArray8[i];
                }
            }
        }
        //????????????????????????
        try {
            organizeCompareWaveData(currentStart);
        } catch (Exception ignored) {
        }
        //??????????????????????????????????????????510??????????????????
        currentMoverPosition510 = currentStart / densityMax;
        //?????????????????????????????????????????????
        int moverPosition;
        moverPosition = mvWave.getParentWidth() * currentMoverPosition510 / 510;
        //????????????????????????
        setHorizontalMoveView();
        //???????????????????????????
        setMoverPosition(moverPosition);

    }

    /**
     * ???????????????????????????????????????    //GC20200611
     */
    private void drawClickZoomCursor() {
        Log.e("?????????-???????????????", "densityMax:" + densityMax + "/density:" + density + "/pointDistance:" + pointDistance + "/positionVirtual" + positionVirtual
                + "/positionReal:" + positionReal + "/zero:" + zero + "/datalength:" + dataLength + "/currentStart:" + currentStart);
        if (pointDistance < 255 * density) {
            //?????????????????????????????????????????????0??????
            currentStart = 0;
            positionVirtual = pointDistance / density;
            //????????????????????????
            if (zero > currentStart + 510 * density) {
                mainWave.setScrubLineRealDisappear();
            } else {
                positionReal = zero / density;
                mainWave.setScrubLineReal(positionReal);
            }
            //??????????????????????????????
            if (mode ==SIM) {
                if (simStandardZero >= currentStart + 510 * density) {
                    mainWave.setScrubLineSimDisappear();
                } else {
                    positionSim = simStandardZero / density;
                    mainWave.setScrubLineSim(positionSim);
                }
            }
        }
        else if ((pointDistance >= 255 * density) && (pointDistance < dataLength - 255 * density)) {
            //???????????????????????????????????????
            currentStart = pointDistance - 255 * density;
            positionVirtual = 255;
            //????????????????????????
            if ( (zero < currentStart) || (zero >= currentStart + 510 * density) ) {
                mainWave.setScrubLineRealDisappear();
            } else {
                positionReal = positionVirtual - (pointDistance - zero) / density;
                mainWave.setScrubLineReal(positionReal);
            }
            //??????????????????????????????
            if (mode == SIM) {
                if ( (zero < currentStart) || (zero >= currentStart + 510 * density) ) {
                    mainWave.setScrubLineSimDisappear();
                } else {
                    positionSim = (simStandardZero - currentStart) / density;
                    mainWave.setScrubLineSim(positionSim);
                }
            }
        }
        else {
            //??????????????????
            currentStart = dataLength - 510 * density;
            positionVirtual = (pointDistance - currentStart) / density;
            //????????????????????????
            if (zero < currentStart) {
                mainWave.setScrubLineRealDisappear();
            } else {
                positionReal = (zero - currentStart) / density;
                mainWave.setScrubLineReal(positionReal);
            }
            //??????????????????????????????
            if (mode == SIM) {
                if (simStandardZero < currentStart) {
                    mainWave.setScrubLineSimDisappear();
                } else {
                    positionSim = (simStandardZero - currentStart)  / density;
                    mainWave.setScrubLineReal(positionReal);
                }
            }
        }
        //????????????
        if (density == densityMax) {
            currentStart = 0;
            positionVirtual = pointDistance / densityMax;
            //????????????
            positionReal = zero / densityMax;
            mainWave.setScrubLineReal(positionReal);
            //???SIM????????????    //GC20200330
            if (mode == SIM) {
                positionSim = simStandardZero / densityMax;
                mainWave.setScrubLineSim(positionSim);
            }
        }
        //????????????
        mainWave.setScrubLineVirtual(positionVirtual);
        Log.e("?????????-????????????????????????", "/pointDistance:" + pointDistance + "/positionVirtual" + positionVirtual
                + "/positionReal:" + positionReal + "/zero:" + zero + "/datalength:" + dataLength + "/currentStart:" + currentStart);

    }

    /**
     * ????????????????????????
     */
    private void setWaveParameter() {
        //?????????????????????????????????
        Constant.ModeValue = mode;
        Constant.RangeValue = range;
        Constant.Gain = gain;
        Constant.Velocity = velocity;
        Constant.DensityMax = densityMax;
        if (density > densityMax) {
            density = densityMax;
            tvZoomValue.setText("1 : " + density);
        }
        //??????????????????????????????
        isDatabase = false;
        //??????????????????
        isCom = false;
        if (mode == TDR) {
            //??????????????????????????????????????????
            dataMax = READ_TDR_SIM[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            dataMax = READ_ICM_DECAY[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
        } else if (mode == SIM) {
            dataMax = READ_TDR_SIM[rangeState];
            waveArray = new int[dataMax];
            Constant.WaveData = new int[dataMax];
            Constant.SimData = new int[dataMax];
            //SIM????????????????????????????????????
            simArray1 = new int[dataMax];
            simArray2 = new int[dataMax];
            simArray3 = new int[dataMax];
            simArray4 = new int[dataMax];
            simArray5 = new int[dataMax];
            simArray6 = new int[dataMax];
            simArray7 = new int[dataMax];
            simArray8 = new int[dataMax];
            //????????????????????????SIM????????????????????????
            isCom = true;

        }

        //SIM????????????????????????????????????positionReal?????????????????????
        if (density == densityMax) {
            if (mode != SIM) {
                zero = positionReal * densityMax;
            }
            pointDistance = positionVirtual * densityMax;
        }

    }

    int min1Pos;
    int min2Pos;
    int min3Pos;
    int min4pos;
    int min5Pos;
    int min6Pos;
    int min7Pos;
    int min8Pos;
    boolean selectSim1;
    boolean selectSim2;
    boolean selectSim3;
    boolean selectSim4;
    boolean selectSim5;
    boolean selectSim6;
    boolean selectSim7;
    boolean selectSim8;
    int sim_g;
    int sim_u;
    int sim_point;
    //int sim_point8;
    /**
     * SIM????????????  //GC20200529
     */
    public void selectBestSim() {
        //??????????????????    //GC20200609
        gainJudgmentSim();
        switch (gainState) {
            case 0:
                tvInformation.setText("");
                break;
            case 1:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_high));
                //????????????1??????
                selectSim = 1;
                setSelectSim(selectSim);
                return;
            case 2:
                gainState = 0;
                //??????????????????
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.gain_too_low));
                //????????????1??????
                selectSim = 1;
                setSelectSim(selectSim);
                return;
            default:
                break;
        }
        //??????1.???????????????????????????
        int sum = 0;
        for (int i = 0, waveNum = 1; i < 8; i++, waveNum++) {
            double p = Math.abs((float) (simSum[waveNum] - simSum[0]) / simSum[0]);
//            if ((p > 0.213 || p < 0.019)) { //A20200606 ??????????????????
            if (p > 0.213 ) {
                //???????????????,????????????
                overlapNum[i] = 0;
                Log.e("SIM??????1",waveNum + "?????????" + " /p = " + p);
            } else {
                //????????????
                overlapNum[i] = waveNum;
                Log.e("SIM??????1",waveNum + "???  ???" + " /p = " + p);
            }
            sum += overlapNum[i];
        }
        if (sum == 0) {
            Log.e("SIM??????1", " ?????????????????????");
            tvInformation.setVisibility(View.VISIBLE);
            tvInformation.setText(getResources().getString(R.string.testAgain));
            //?????????????????????1  //GC20200601
            selectSim = 1;
            setSelectSim(selectSim);
        }
        else {
            //??????2.??????????????????
            int j = pulseRemove[rangeState] + 3;
            int maxNum = 0;
            int[] maxData = new int[65560];
            int[] maxDataPos = new int[65560];
            //?????????????????????????????????????????????????????????????????????
            while ( (j >= pulseRemove[rangeState] + 3) && (j < dataMax - removeTdrSim[rangeState]) ) {
                if ( (waveArray[j] > waveArray[j - 1]) && (waveArray[j] >= waveArray[j + 1]) ) {
                    if (waveArray[j - 1] >= waveArray[j - 2]) {
                        if (waveArray[j - 2] > waveArray[j - 3]) {
                            maxData[maxNum] = waveArray[j];
                            maxDataPos[maxNum] = j;
//                            Log.e("SIM??????2", " /??????????????? = " + maxData[maxNum] + " /??????????????? = " + maxDataPos[maxNum]);
                            maxNum++;
                        }
                    }
                }
                j++;
            }
            if (maxNum == 0) {
                Log.e("SIM??????2", "???????????????");
                tvInformation.setVisibility(View.VISIBLE);
                tvInformation.setText(getResources().getString(R.string.testAgain));
                //????????????????????????1  //GC20200601
                selectSim = 1;
                setSelectSim(selectSim);
            }
            else {
                int max = maxData[0];
                int maxPos = maxDataPos[0];
                for (int k = 0; k < maxNum; k++) {
                    if(maxData[k] >= max) {
                        max = maxData[k];
                        maxPos = maxDataPos[k];
                    }
                }
                Log.e("SIM??????2", " /????????????????????? = " + maxPos);
                //3.??????????????????????????????????????????????????????????????????????????????
                for (int l = 0; l < 8; l++) {
                    //????????????????????????
                    if (overlapNum[l] == 1) {
                        int i1 = pulseRemove[rangeState] + 5;
                        int minNum1 = 0;
                        int[] minData1 = new int[65560];
                        int[] minDataPos1 = new int[65560];
                        while ( (i1 >= pulseRemove[rangeState] + 5) && (i1 < dataMax - removeTdrSim[rangeState]) ) {
                            if ((simArray1[i1] < simArray1[i1 - 1]) && (simArray1[i1] <= simArray1[i1 + 1])) {
                                if (simArray1[i1 - 1] <= simArray1[i1 - 2]) {
                                    if (simArray1[i1 - 2] <= simArray1[i1 - 3]) {
                                        if (simArray1[i1 - 3] <= simArray1[i1 - 4]) {
                                            if (simArray1[i1 - 4] <= simArray1[i1 - 5]) {
                                                minData1[minNum1] = simArray1[i1];
                                                minDataPos1[minNum1] = i1;
                                                minNum1++;
                                            }
                                        }
                                    }
                                }
                            }
                            i1++;
                        }
                        if (minNum1 > 0) {
                            int min1 = minData1[0];
                            for(int k1 = 0; k1 < minNum1; k1++) {
                                if(minData1[k1] <= min1) {
                                    min1 = minData1[k1];
                                    min1Pos = minDataPos1[k1];
                                }
                            }
                            //????????????????????????????????????????????????????????????   //20200601 ????????????????????????
                            if (min1Pos < maxPos + 60) { // if (min1Pos < maxPos + 30) { //20200610
                                selectSim1 = true;
                                Log.e("SIM??????2", "1?????????????????????  " + " /min1Pos = " + min1Pos);
                            } else {
                                Log.e("SIM??????2", "1????????????????????????" + " /min1Pos = " + min1Pos);
                            }
                        } else {
                            Log.e("SIM??????2", "1??????????????????");
                        }

                    }

                    if (overlapNum[l] == 2) {
                        int i2 = pulseRemove[rangeState] + 5;
                        int minNum2 = 0;
                        int[] minData2 = new int[65560];
                        int[] minDataPos2 = new int[65560];
                        while ( (i2 >= pulseRemove[rangeState] + 5) && (i2 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray2[i2] < simArray2[i2 - 1]) && (simArray2[i2] <= simArray2[i2 + 1]) ) {
                                if (simArray2[i2 - 1] <= simArray2[i2 - 2]) {
                                    if (simArray2[i2 - 2] <= simArray2[i2 - 3]) {
                                        if (simArray2[i2 - 3] <= simArray2[i2 - 4]) {
                                            if (simArray2[i2 - 4] <= simArray2[i2 - 5]) {
                                                minData2[minNum2] = simArray2[i2];
                                                minDataPos2[minNum2] = i2;
                                                minNum2++;
                                            }
                                        }
                                    }
                                }
                            }
                            i2++;
                        }
                        if (minNum2 >= 1) {
                            int min2 = minData2[0];
                            for(int k2 = 0; k2 < minNum2; k2++) {
                                if(minData2[k2] <= min2) {
                                    min2 = minData2[k2];
                                    min2Pos = minDataPos2[k2];
                                }
                            }
                            if (min2Pos < maxPos + 60) {
                                selectSim2 = true;
                                Log.e("SIM??????2", "2?????????????????????  " + " /min2Pos = " + min2Pos);
                            } else {
                                Log.e("SIM??????2", "2????????????????????????" + " /min2Pos = " + min2Pos);
                            }
                        } else {
                            Log.e("SIM??????2", "2??????????????????");
                        }

                    }

                    if (overlapNum[l] == 3) {
                        int i3 = pulseRemove[rangeState] + 5;
                        int minNum3 = 0;
                        int[] minData3 = new int[65560];
                        int[] minDataPos3 = new int[65560];
                        while ( (i3 >= pulseRemove[rangeState] + 5) && (i3 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray3[i3] < simArray3[i3 - 1]) && (simArray3[i3] <= simArray3[i3 + 1]) ) {
                                if (simArray3[i3 - 1] <= simArray3[i3 - 2]) {
                                    if (simArray3[i3 - 2] <= simArray3[i3 - 3]) {
                                        if (simArray3[i3 - 3] <= simArray3[i3 - 4]) {
                                            if (simArray3[i3 - 4] <= simArray3[i3 - 5]) {
                                                minData3[minNum3] = simArray3[i3];
                                                minDataPos3[minNum3] = i3;
                                                minNum3++;
                                            }
                                        }
                                    }
                                }
                            }
                            i3++;
                        }
                        if (minNum3 >= 1) {
                            int min3 = minData3[0];
                            for(int k3 = 0; k3 < minNum3; k3++) {
                                if(minData3[k3] <= min3) {
                                    min3 = minData3[k3];
                                    min3Pos = minDataPos3[k3];
                                }
                            }
                            if (min3Pos < maxPos + 60) {
                                selectSim3 = true;
                                Log.e("SIM??????2", "3?????????????????????  " + " /min3Pos = " + min3Pos);
                            } else {
                                Log.e("SIM??????2", "3????????????????????????" + " /min3Pos = " + min3Pos);
                            }
                        }else {
                            Log.e("SIM??????2", "3??????????????????");
                        }

                    }

                    if (overlapNum[l] == 4) {
                        int i4 = pulseRemove[rangeState] + 5;
                        int minNum4 = 0;
                        int[] minData4 = new int[65560];
                        int[] minDataPos4 = new int[65560];
                        while ((i4 >= pulseRemove[rangeState] + 5) && (i4 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray4[i4] < simArray4[i4 - 1]) && (simArray4[i4] <= simArray4[i4 + 1]) ) {
                                if (simArray4[i4 - 1] <= simArray4[i4 - 2]) {
                                    if (simArray4[i4 - 2] <= simArray4[i4 - 3]) {
                                        if (simArray4[i4 - 3] <= simArray4[i4 - 4]) {
                                            if (simArray4[i4 - 4] <= simArray4[i4 - 5]) {
                                                minData4[minNum4] = simArray4[i4];
                                                minDataPos4[minNum4] = i4;
                                                minNum4++;
                                            }
                                        }
                                    }
                                }
                            }
                            i4++;
                        }
                        if (minNum4 >= 1) {
                            int min4 = minData4[0];
                            for(int k4 = 0; k4 < minNum4; k4++) {
                                if(minData4[k4] <= min4) {
                                    min4 = minData4[k4];
                                    min4pos = minDataPos4[k4];
                                }
                            }
                            if (min4pos < maxPos + 60) {
                                selectSim4 = true;
                                Log.e("SIM??????2", "4?????????????????????  " + " /min4pos = " + min4pos);
                            }else {
                                Log.e("SIM??????2", "4????????????????????????" + " /min4pos = " + min4pos);
                            }
                        }else {
                            Log.e("SIM??????2", "4??????????????????");
                        }

                    }

                    if (overlapNum[l] == 5) {
                        int i5 = pulseRemove[rangeState] + 5;
                        int minNum5 = 0;
                        int[] minData5 = new int[65560];
                        int[] minDataPos5 = new int[65560];
                        while ((i5 >= pulseRemove[rangeState] + 5) && (i5 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray5[i5] < simArray5[i5 - 1]) && (simArray5[i5] <= simArray5[i5 + 1]) ) {
                                if (simArray5[i5 - 1] <= simArray5[i5 - 2]) {
                                    if (simArray5[i5 - 2] <= simArray5[i5 - 3]) {
                                        if (simArray5[i5 - 3] <= simArray5[i5 - 4]) {
                                            if (simArray5[i5 - 4] <= simArray5[i5 - 5]) {
                                                minData5[minNum5] = simArray5[i5];
                                                minDataPos5[minNum5] = i5;
                                                minNum5++;
                                            }
                                        }
                                    }
                                }
                            }
                            i5++;
                        }
                        if (minNum5 >= 1) {
                            int min5 = minData5[0];
                            for(int k5 = 0; k5 < minNum5; k5++) {
                                if(minData5[k5] <= min5) {
                                    min5 = minData5[k5];
                                    min5Pos = minDataPos5[k5];
                                }
                            }
                            if (min5Pos < maxPos + 60) {
                                selectSim5 = true;
                                Log.e("SIM??????2", "5?????????????????????  " + " /min5Pos = " + min5Pos);
                            }else {
                                Log.e("SIM??????2", "5????????????????????????" + " /min5Pos = " + min5Pos);
                            }
                        }else {
                            Log.e("SIM??????2", "5??????????????????");
                        }

                    }

                    if (overlapNum[l] == 6) {
                        int i6 = pulseRemove[rangeState] + 5;
                        int minNum6 = 0;
                        int[] minData6 = new int[65560];
                        int[] minDataPos6 = new int[65560];
                        while ((i6 >= pulseRemove[rangeState] + 5) && (i6 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray6[i6] < simArray6[i6 - 1]) && (simArray6[i6] <= simArray6[i6 + 1]) ) {
                                if (simArray6[i6 - 1] <= simArray6[i6 - 2]) {
                                    if (simArray6[i6 - 2] <= simArray6[i6 - 3]) {
                                        if (simArray6[i6 - 3] <= simArray6[i6 - 4]) {
                                            if (simArray6[i6 - 4] <= simArray6[i6 - 5]) {
                                                minData6[minNum6] = simArray6[i6];
                                                minDataPos6[minNum6] = i6;
                                                minNum6++;
                                            }
                                        }
                                    }
                                }
                            }
                            i6++;
                        }
                        if (minNum6 >= 1) {
                            int min6 = minData6[0];
                            for(int k6 = 0; k6 < minNum6; k6++) {
                                if(minData6[k6] <= min6) {
                                    min6 = minData6[k6];
                                    min6Pos = minDataPos6[k6];
                                }
                            }
                            if (min6Pos < maxPos + 60) {
                                selectSim6 = true;
                                Log.e("SIM??????2", "6?????????????????????  " + " /min6Pos" + min6Pos);
                            }else {
                                Log.e("SIM??????2", "6????????????????????????" + " /min6Pos = " + min6Pos);
                            }
                        }else {
                            Log.e("SIM??????2", "6??????????????????");
                        }
                    }

                    if (overlapNum[l] == 7) {
                        int i7 = pulseRemove[rangeState] + 5;
                        int minNum7 = 0;
                        int[] minData7 = new int[65560];
                        int[] minDataPos7 = new int[65560];
                        while ( (i7 >= pulseRemove[rangeState] + 5) && (i7 < dataMax - removeTdrSim[rangeState]) ) {
                            if ( (simArray7[i7] < simArray7[i7 - 1]) && (simArray7[i7] <= simArray7[i7 + 1]) ) {
                                if (simArray7[i7 - 1] <= simArray7[i7 - 2]) {
                                    if (simArray7[i7 - 2] <= simArray7[i7 - 3]) {
                                        if (simArray7[i7 - 3] <= simArray7[i7 - 4]) {
                                            if (simArray7[i7 - 4] <= simArray7[i7 - 5]) {
                                                minData7[minNum7] = simArray7[i7];
                                                minDataPos7[minNum7] = i7;
                                                minNum7++;
                                            }
                                        }
                                    }
                                }
                            }
                            i7++;
                        }
                        if (minNum7 >= 1) {
                            int min7 = minData7[0];
                            for(int k7 = 0; k7 < minNum7; k7++) {
                                if(minData7[k7] <= min7) {
                                    min7 = minData7[k7];
                                    min7Pos = minDataPos7[k7];
                                }
                            }
                            if (min7Pos < maxPos + 60) {
                                selectSim7 = true;
                                Log.e("SIM??????2", "7?????????????????????  " + " /min7Pos = " + min7Pos);
                            }else {
                                Log.e("SIM??????2", "7????????????????????????" + " /min7Pos = " + min7Pos);
                            }
                        }else {
                            Log.e("SIM??????2", "7??????????????????");
                        }
                    }

                    if (overlapNum[l] == 8) {
                        int i8 = pulseRemove[rangeState] + 5;
                        int minNum8 = 0;
                        int[] minData8 = new int[65560];
                        int[] minDataPos8 = new int[65560];
                        while ( (i8 >= pulseRemove[rangeState] + 5) && (i8 < dataMax - removeTdrSim[rangeState]) ) {
                            if((simArray8[i8] < simArray8[i8 - 1]) && (simArray8[i8] <= simArray8[i8 + 1])) {
                                if((i8 > pulseRemove[rangeState] + 5) && (simArray8[i8 - 1] <= simArray8[i8 - 2]) ) {
                                    if((simArray8[i8 - 2] <= simArray8[i8 - 3]) ){
                                        if((simArray8[i8 - 3] <= simArray8[i8 - 4]) ){
                                            if((simArray8[i8 - 4] < simArray8[i8 - 5]) ) {
                                                minData8[minNum8] = simArray8[i8];
                                                minDataPos8[minNum8] = i8;
                                                minNum8++;
                                            }
                                        }
                                    }
                                }
                            }
                            i8++;
                        }
                        if (minNum8 >= 1) {
                            int min8 = minData8[0];
                            for(int k8 = 0; k8 < minNum8; k8++) {
                                if(minData8[k8] <= min8) {
                                    min8 = minData8[k8];
                                    min8Pos = minDataPos8[k8];
                                }
                            }
                            if (min8Pos < maxPos + 60) {
                                selectSim8 = true;
                                Log.e("SIM??????2", "8?????????????????????  " + " /min8Pos = " + min8Pos + " /maxPos = " + maxPos);
                            }else {
                                Log.e("SIM??????2", "8????????????????????????" + " /min8Pos = " + min8Pos + " /maxPos = " + maxPos);
                            }
                        }else {
                            Log.e("SIM??????2", "8??????????????????");
                        }
                    }
                }
            }
        }
        //??????3.????????????????????????????????????
        simRelevantJudgment();

        //jk20200804  ??????????????????
        pointDistance = sim_point;
        zero = simOriginalZero;
        //Log.e("SIMc2", "min1_pos"+min1Pos);
        Log.e("SIMc2", "sim_u"+sim_u);
        Log.e("SIMc2", "sim_g"+sim_g);
        Log.e("SIMc2", "pointDistance"+sim_point);
        if (range == RANGE_250) {
            zero = simOriginalZero * 2;
            pointDistance=sim_point*2;
        }
        if (zero >= (currentMoverPosition510 * dataLength / 510) && zero <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
            positionReal = (zero - (currentMoverPosition510 * dataLength / 510)) / density;
            mainWave.setScrubLineReal(positionReal);
        } else {
            mainWave.setScrubLineRealDisappear();
        }


        //?????????????????????
       /* if (sim_point >= (currentMoverPosition510 * dataLength / 510) && sim_point <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
            positionVirtual = (sim_point - (currentMoverPosition510 * dataLength / 510)) / density;
            mainWave.setScrubLineVirtual(positionVirtual);
        } else {
            mainWave.setScrubLineVirtualDisappear();
        }*/
        //?????????????????????    //jk20200826
        if (pointDistance >= (currentMoverPosition510 * dataLength / 510) && pointDistance <= ((currentMoverPosition510 * dataLength / 510) + (510 * density))) {
            positionVirtual = (pointDistance - (currentMoverPosition510 * dataLength / 510)) / density;
            mainWave.setScrubLineVirtual(positionVirtual);
        } else {
            mainWave.setScrubLineVirtualDisappear();
        }
        calculateDistance(Math.abs(pointDistance - zero));

    }

    /**
     * ????????????????????????????????????
     */
    private void gainJudgmentSim() {
        int i;
        int max = 0;
        int sub;

        //?????????????????????????????????
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
          //  sub = waveArray[i] - 133;
            sub = waveArray[i] - Median_value; //jk20210520
            if (Math.abs(sub) > max) {
                max = Math.abs(sub);
            }
        }
        if (max <= 29) {  //jk20201130  //max<=41
            //????????????????????????????????????????????? 15% 38
            gainState = 2;
            return;
        }
        for (i = 0; i < dataMax - removeTdrSim[rangeState]; i++) {
            if ((waveArray[i] > 242) || (waveArray[i] < 20)) {
//            if ((waveArray[i] > 242) || (waveArray[i] < 13)) {    //A20200527  SIM????????????????????????
                //??????????????????
                gainState = 1;
                return;
            }
        }
    }

    /**
     * ????????????
     */
    int n,n1,n2,n3,n4,n5,n6,n7,n8;
    public void simRelevantJudgment() {
        simFilter();
        int selectWaveNum = 1;
        double r, r1;
        double rMax = 0.0;

        n = dataMax - removeTdrSim[rangeState];

        if (selectSim1) {
            n1 = min1Pos;
            //??????????????????
            while (n1 > 1) {
                if (simArray1[n1] <= simArray1[n1-1]) {
                   // if (simArray1[n1] != simArray1[n1-2]) {
                    if ((Median_value  - simArray1[n1]) >= 0) {
                        n1 = n1 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            //??????????????????
//            r = correlationCalculation(waveArray, simArray1, n1);
            r = correlationCalculation(simArray0Filter, simArray1Filter, n1);
            r1 = correlationCalculation(simArray0Filter, simArray1Filter, n);
            Log.e("SIM??????3", "1 ???????????? r1 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n1);
            //GC20200609
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 1;
                    Log.e("SIM??????3", "????????????1???");
                }
            }
        }

        if (selectSim2) {
            n2 = min2Pos;
            while (n2 > 1) {
                if (simArray2[n2] <= simArray2[n2-1]) {
                 //   if (simArray2[n2] != simArray2[n2-2]) {
                    if ((Median_value  - simArray2[n2]) >= 0) {
                        n2 = n2 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray2Filter, n2);
            r1 = correlationCalculation(simArray0Filter, simArray2Filter, n);
            Log.e("SIM??????3", "2 ???????????? r2 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n2);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 2;
                    Log.e("SIM??????3", "????????????2???");
                }
            }
        }

        if (selectSim3) {
            n3 = min3Pos;
            while (n3 > 1) {
                if (simArray3[n3] <= simArray3[n3-1]) {
                  //  if (simArray3[n3] != simArray3[n3-2]) {
                    if ((Median_value  - simArray3[n3]) >= 0) {
                        n3 = n3 - 1 ;
                    }else{
                        break;
                    }
                } else{
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray3Filter, n3);
            r1 = correlationCalculation(simArray0Filter, simArray3Filter, n);
            Log.e("SIM??????3", "3 ????????????r3 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n3);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 3;
                    Log.e("SIM??????3", "????????????3???");
                }
            }
        }

        if (selectSim4) {
            n4 = min4pos;
            while (n4 > 1) {
                if (simArray4[n4] <= simArray4[n4 - 1]) {
                    //if (simArray4[n4] != simArray4[n4-2]) {
                    if ((Median_value  - simArray4[n4]) >= 0) {
                        n4 = n4 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray4Filter, n4);
            r1 = correlationCalculation(simArray0Filter, simArray4Filter, n);
            Log.e("SIM??????3", "4 ???????????? r4 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n4);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 4;
                    Log.e("SIM??????3", "????????????4???");
                }
            }
        }

        if (selectSim5) {
            n5 = min5Pos;
            while (n5 > 1) {
                if (simArray5[n5] <= simArray5[n5 - 1]) {
                   // if (simArray5[n5] != simArray5[n5-2]) {
                    if ((Median_value  - simArray5[n5]) >= 0) {
                        n5 = n5 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray5Filter, n5);
            r1 = correlationCalculation(simArray0Filter, simArray5Filter, n);
            Log.e("SIM??????3", "5 ???????????? r5 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n5);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 5;
                    Log.e("SIM??????3", "????????????5???");
                }
            }
        }

        if (selectSim6) {
            n6 = min6Pos;
            while (n6 > 1) {
                if (simArray6[n6] <= simArray6[n6 - 1]) {
                 //   if (simArray6[n6] != simArray6[n6-2]) {
                    if ((Median_value  - simArray6[n6]) >= 0) {
                        n6 = n6 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray6Filter, n6);
            r1 = correlationCalculation(simArray0Filter, simArray6Filter, n);
            Log.e("SIM??????3", "6 ???????????? r6 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n6);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 6;
                    Log.e("SIM??????3", "????????????6???");
                }
            }
        }

        if (selectSim7) {
            n7 = min7Pos;
            while (n7 > 1) {
                if (simArray7[n7] <= simArray7[n7 - 1]) {
                 //   if (simArray7[n7] != simArray7[n7-2]) {
                    if ((Median_value  - simArray7[n7]) >= 0) {
                        n7 = n7 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray7Filter, n7);
            r1 = correlationCalculation(simArray0Filter, simArray7Filter, n);
            Log.e("SIM??????3", "7 ???????????? r7 = " + r + " /?????????????????? = " + r1  + " /??????????????????" + n7);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 7;
                    Log.e("SIM??????3", "????????????7???");
                }
            }
        }

        if(selectSim8) {
            n8 = min8Pos;
            while (n8 > 1) {
                if (simArray8[n8] <= simArray8[n8 - 1]) {
                   // if (simArray8[n8] != simArray8[n8-2]) {
                    if ((Median_value  - simArray8[n8]) >= 0) {
                        n8 = n8 - 1 ;
                    }else{
                        break;
                    }
                } else {
                    break;
                }
            }
            r = correlationCalculation(simArray0Filter, simArray8Filter, n8);
            r1 = correlationCalculation(simArray0Filter, simArray8Filter, n);
            Log.e("SIM??????3", "8 ???????????? r8 = " + r + " /?????????????????? = " + r1 + " /??????????????????" + n8);
            if ( (r - r1) > 0.1 ) {
                if (r > rMax) {
                    rMax = r;
                    selectWaveNum = 8;
                    Log.e("SIM??????3", "????????????8???");
                }
            }
        }

        if (rMax > 0) {
            //???????????????
            tvInformation.setVisibility(View.VISIBLE);
            tvInformation.setText("");
            //????????????
            selectSim = selectWaveNum;
            setSelectSim(selectSim);

        } else {
            //??????????????????
            tvInformation.setVisibility(View.VISIBLE);
            tvInformation.setText(getResources().getString(R.string.testAgain));
            //?????????????????????1  //GC20200601
            selectSim = 1;
            setSelectSim(selectSim);
        }

        //????????????  //jk20200804
        switch(selectWaveNum) {
            case 1:
                sim_g = min1Pos;
                sim_u = n1;
                simArray = simArray1;
                break;
            case 2:
                sim_g = min2Pos;
                sim_u = n2;
                simArray = simArray2;
                break;
            case 3:
                sim_g = min3Pos;
                sim_u = n3;
                simArray = simArray3;
                break;
            case 4:
                sim_g = min4pos;
                sim_u = n4;
                simArray = simArray4;
                break;
            case 5:
                sim_g = min5Pos;
                sim_u = n5;
                simArray = simArray5;
                break;
            case 6:
                sim_g = min6Pos;
                sim_u = n6;
                simArray = simArray6;
                break;
            case 7:
                sim_g = min7Pos;
                sim_u = n7;
                simArray = simArray7;
                break;
            case 8:
                sim_g = min8Pos;
                sim_u = n8;
                simArray = simArray8;
                break;
            default:
                break;
        }
        if(sim_u < 0){
            sim_u = 0;
        }
        Log.e("SIM", "sim_u"+sim_u);
        Log.e("SIM", "sim_g"+sim_g);
        int[] simArray1_8 = new int[60050];
        for(int i = sim_u; i < sim_g; i++){
            simArray1_8[i] = simArray[i] - Median_value; //jk20210520
        }

        double[] X = new double[1000];
        double[] Y = new double[1000];
        double[] atemp = new double[8];
        double[] b = new double[4];
        double[][] a = new double[4][4];


        for (int h = sim_u; h < sim_g; h++) {
            X[h - sim_u] = h - sim_u;
            Y[h - sim_u] = simArray1_8[h];
        }
        for (int i = 0; i < sim_g - sim_u; i++) {
            atemp[1] += X[i];
            atemp[2] += Math.pow(X[i], 2);
            atemp[3] += Math.pow(X[i], 3);
            atemp[4] += Math.pow(X[i], 4);
            atemp[5] += Math.pow(X[i], 5);
            atemp[6] += Math.pow(X[i], 6);
            b[0] += Y[i];
            b[1] += X[i] * Y[i];
            b[2] += Math.pow(X[i], 2) * Y[i];
            b[3] += Math.pow(X[i], 3) * Y[i];
        }

        atemp[0] = sim_g - sim_u;

        for (int i1 = 0; i1 < 4; i1++) {
            int k = i1;
            for (int j = 0; j < 4; j++) {
                a[i1][j] = atemp[k++];
            }
        }

        for (int k = 0; k < 3; k++) {
            int column = k;
            double mainelement = a[k][k];
            for (int i2 = k; i2 < 4; i2++) {
                if (Math.abs((a[i2][k])) > mainelement) {
                    mainelement = Math.abs((a[i2][k]));
                    column = i2;
                }
            }

            for (int j = k; j < 4; j++) {
                double atemp_1 = a[k][j];
                a[k][j] = a[column][j];
                a[column][j] = atemp_1;
            }

            double btemp = b[k];
            b[k] = b[column];
            b[column] = btemp;

            for (int i3 = k + 1; i3 < 4; i3++) {
                double Mik = a[i3][k] / a[k][k];
                for (int j = k; j < 4; j++) {
                    a[i3][j] -= Mik * a[k][j];
                }
                b[i3] -= Mik * b[k];

            }
        }

        b[3] /= a[3][3];

        for (int i = 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < 4; j++) {
                sum += a[i][j] * b[j];
            }
            b[i] = (b[i] - sum) / a[i][i];
        }
        int sim_point8=0;
       //??????????????????????????????????????????0?????????????????????  ????????????????????????  //jk20210527
        sim_point8 = equationSolving(b[3],b[2],b[1],b[0],-5,5) + sim_u;


     /*   double[] sim_c1 = new double[1000];
        //int sim_point8=0;

        for(int x=0;x<sim_g-sim_u;x++)  {
            sim_c1[x]=b[3]*x*x*x+b[2]*x*x+b[1]*x+b[0];
        }
        for(int i=0; i<sim_g-sim_u;i++){
            if ((((sim_c1[i]) <= 0) && ((sim_c1[i + 1]) > 0)) || ((sim_c1[i]) >= 0) && ((sim_c1[i + 1]) < 0)){
               // int x = waveArray[i] - 134 - simArray1_8[i];
                //GC20200817    ????????????????????????
              //  if (x <= 10) {
                    sim_point8 = i + sim_u + 1;
                    Log.e("SIMc2", " /i = " + i);
            //   }
            }else{
                for (int f = 0; f < sim_g-sim_u - 1; f++) {
                    if ((((sim_c1[f]) <= 3) && ((sim_c1[f + 1]) > 3)) || (((sim_c1[f]) >= 3) && ((sim_c1[f + 1]) < 3))) {
                       // int x = waveArray[i] - 134 - simArray1_8[i];
                    //   if (x <= 10) {
                            int z1 = f;
                            sim_point8 = z1 + sim_u + 1;
                            Log.e("SIMc2", " /z1 = " + z1);
                      //  }
                    }
                }
            }
        }*/
        if(sim_point8<=0){            //jk20200820 ???????????????0?????????
            sim_point8 = sim_u  ;
        }
        sim_point = sim_point8;

        //????????????
        selectSim1 = false;
        selectSim2 = false;
        selectSim3 = false;
        selectSim4 = false;
        selectSim5 = false;
        selectSim6 = false;
        selectSim7 = false;
        selectSim8 = false;

    }

    /*
    **  ????????????????????????  //jk20210527
     */
    public int  equationSolving(double a,double b,double c,double d,int x1,int x2) {
           double fx1,fx2,fx0;
           int x0;
           int num=0;

        //?????????f(x1)???f(x2)????????????x1 < x2
        fx1 = ((a * x1 + b) * x1 + c) * x1 + d;
        fx2 = ((a * x2 + b) * x2 + c) * x2 + d;
        if( fx1 * fx2 >= 0 ){
            //??????????????????
            x1 = x1 / 2;
            x2 = x2 / 2;
            fx1 = ((a * x1 + b) * x1 + c) * x1 + d;
            fx2 = ((a * x2 + b) * x2 + c) * x2 + d;
            if( fx1 * fx2 > 0 ) {
                x1 = x1 / 2;
                x2 = x2 / 2;
                fx1 = ((a * x1 + b) * x1 + c) * x1 + d;
                fx2 = ((a * x2 + b) * x2 + c) * x2 + d;
                if( fx1 * fx2 > 0 ) {
                    x0 = 0;
                }else{
                    do {
                        x0 = (x1 + x2) / 2;
                        fx0 = ((a * x0 + b) * x0 + c) * x0 + d;
                        num++;

                        if(num < 10) {
                            if ((fx0 * fx1) < 0) {
                                x2 = x0;
                                fx2 = fx0;
                            } else {
                                x1 = x0;
                                fx1 = fx0;
                            }
                        }else{
                            break;
                        }
                    } while (Math.abs(fx0) >= 0.1);//????????????
                }
            }else{
                do {
                    x0 = (x1 + x2) / 2;
                    fx0 = ((a * x0 + b) * x0 + c) * x0 + d;
                    num++;

                    if(num < 10) {
                        if ((fx0 * fx1) < 0) {
                            x2 = x0;
                            fx2 = fx0;
                        } else {
                            x1 = x0;
                            fx1 = fx0;
                        }
                    }else{
                        break;
                    }
                } while (Math.abs(fx0) >= 0.1);//????????????
            }
        }else {
            do {
                x0 = (x1 + x2) / 2;
                fx0 = ((a * x0 + b) * x0 + c) * x0 + d;
                num++;

                if(num < 10) {
                    if ((fx0 * fx1) < 0) {
                        x2 = x0;
                        fx2 = fx0;
                    } else {
                        x1 = x0;
                        fx1 = fx0;
                    }
                }else{
                    break;
                }
            } while (Math.abs(fx0) >= 0.1);//????????????

        }
            return x0;
    }


    /**
     * ???????????????????????????????????????????????????????????????
     * k=0.9987;
     * k=0.9139  1.5M
     * k=0.9409  1M
     */
    public void simFilter() {
        double k = 0.9139;
       // simArray0Filter[0] = (double) (waveArray[0] - 133);
        simArray0Filter[0] = (double) (waveArray[0] - Median_value); //jk20210520
        for (int i = 1; i < dataMax; i++) {
            simArray0Filter[i] = k * simArray0Filter[i - 1] + k * (double) (waveArray[i] - waveArray[i - 1]);
        }
        //simArray1Filter[0] = (double) (simArray1[0] - 133);
        simArray1Filter[0] = (double) (simArray1[0] - Median_value);//jk20210520
        for (int i = 1; i < dataMax; i++) {
            simArray1Filter[i] = k * simArray1Filter[i - 1] + k * (double) (simArray1[i] - simArray1[i - 1]);
        }
       // simArray2Filter[0] = (double) (simArray2[0] - 133);
        simArray2Filter[0] = (double) (simArray2[0] - Median_value); //jk20210520
        for (int i = 1; i < dataMax; i++) {
            simArray2Filter[i] = k * simArray2Filter[i - 1] + k * (double) (simArray2[i] - simArray2[i - 1]);
        }
      //  simArray3Filter[0] = (double) (simArray3[0] - 133);
        simArray3Filter[0] = (double) (simArray3[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray3Filter[i] = k * simArray3Filter[i - 1] + k * (double) (simArray3[i] - simArray3[i - 1]);
        }
        //simArray4Filter[0] = (double) (simArray4[0] - 133);
        simArray4Filter[0] = (double) (simArray4[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray4Filter[i] = k * simArray4Filter[i - 1] + k * (double) (simArray4[i] - simArray4[i - 1]);
        }
        //simArray5Filter[0] = (double) (simArray5[0] - 133);
        simArray5Filter[0] = (double) (simArray5[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray5Filter[i] = k * simArray5Filter[i - 1] + k * (double) (simArray5[i] - simArray5[i - 1]);
        }
      //  simArray6Filter[0] = (double) (simArray6[0] - 133);
        simArray6Filter[0] = (double) (simArray6[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray6Filter[i] = k * simArray6Filter[i - 1] + k * (double) (simArray6[i] - simArray6[i - 1]);
        }
       // simArray7Filter[0] = (double) (simArray7[0] - 133);
        simArray7Filter[0] = (double) (simArray7[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray7Filter[i] = k * simArray7Filter[i - 1] + k * (double) (simArray7[i] - simArray7[i - 1]);
        }
        //simArray8Filter[0] = (double) (simArray8[0] - 133);
        simArray8Filter[0] = (double) (simArray8[0] - Median_value);
        for (int i = 1; i < dataMax; i++) {
            simArray8Filter[i] = k * simArray8Filter[i - 1] + k * (double) (simArray8[i] - simArray8[i - 1]);
        }

    }

    /**
     * ??????????????????
     */
    public double correlationCalculation(double[] a, double[] b, int n) {
        double d1, d2, d3;
        double mx, my;
        int i;
        d1 = d2 = d3 = mx = my = 0.0;

        for(i = 0; i < n; i++) {
            mx += a[i];
            my += b[i];
        }
        mx = mx / n;
        my = my / n;

        //???????????????????????????????????????
        for(i = 0; i < n; i++) {
            d1 += (a[i] - mx) * (b[i] - my);
            d2 += (a[i] - mx) * (a[i] - mx);
            d3 += (b[i] - my) * (b[i] - my);
        }
//        Log.e("SIM??????3", " /d1 = " + d1 + " /d2 = " + d2 + " /d3 = " + d3);
        d2 = Math.sqrt(d2 * d3);
        if (d2 == 0) {
            d1 = -1;
        } else {
            d1 = d1 / d2;
        }
        return d1;

    }

    /**
     * ??????????????????????????????????????????  //GC20190713
     */
    public void setDateBaseParameter() {
        //???????????????????????????
        isDatabase = true;
        //??????????????????????????????????????????????????????????????????????????????
        setModeNoCmd(Constant.Para[0]);
        setRangeNoCmd(Constant.Para[1]);
        setGain(Constant.Para[2]);
        setVelocityNoCmd(Constant.Para[3]);
        Constant.ModeValue = Constant.Para[0];
        Constant.RangeValue = Constant.Para[1];
        Constant.Gain = Constant.Para[2];
        Constant.Velocity = Constant.Para[3];
        //??????????????????????????????
        zero = Constant.PositionR;
        pointDistance = Constant.PositonV;
        positionVirtual = pointDistance / densityMax;
        positionReal = zero / densityMax;
        mainWave.setScrubLineVirtual(positionVirtual);
        mainWave.setScrubLineReal(positionReal);
        Constant.DensityMax = densityMax;
        //??????????????????   //20200522  ????????????????????????
        Constant.CurrentLocation = Constant.SaveLocation;
        if (Constant.CurrentUnit == MI_UNIT) {
            tvDistance.setText(new DecimalFormat("0.00").format(Constant.SaveLocation));
        } else {
            tvDistance.setText(UnitUtils.miToFt(Constant.SaveLocation));
        }
        //??????????????????
        isCom = false;
        //??????????????????????????????????????????
        if (mode == TDR) {
            dataMax = READ_TDR_SIM[rangeState];
        } else if ((mode == ICM) || (mode == ICM_DECAY)  || (mode == DECAY)) {
            dataMax = READ_ICM_DECAY[rangeState];
        } else if (mode == SIM) {
            dataMax = READ_TDR_SIM[rangeState];
            //????????????????????????SIM????????????????????????
            isCom = true;
        }
        initMode();
    }

    /**
     * @param mode ???????????????????????????????????? / ???????????????????????????
     */
    public void setMode(int mode) {
        this.mode = mode;
        command = COMMAND_MODE;
        dataTransfer = mode;
        switch (mode) {
            case TDR:
                tvMode.setText(getResources().getString(R.string.btn_tdr));
                //GC20190709
                switchDensity();
                initCursor();
                break;
            case ICM:
                tvMode.setText(getResources().getString(R.string.btn_icm));
                switchDensity();
                initCursor();
                break;
            case ICM_DECAY:
                tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                switchDensity();
                initCursor();
                break;
            case SIM:
                tvMode.setText(getResources().getString(R.string.btn_sim));
                switchDensity();
                initCursor();
                break;
            case DECAY:
                tvMode.setText(getResources().getString(R.string.btn_decay));
                switchDensity();
                initCursor();
                break;
            default:
                break;
        }
        startService();
    }

    public void setModeNoCmd(int mode) {
        this.mode = mode;
        command = COMMAND_MODE;
        dataTransfer = mode;
        startService();
        switch (mode) {
            case TDR:
                tvMode.setText(getResources().getString(R.string.btn_tdr));
                //GC20190709
                switchDensity();
                break;
            case ICM:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_icm));
                break;
            case ICM_DECAY:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_icm_decay));
                break;
            case SIM:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_sim));
                break;
            case DECAY:
                switchDensity();
                tvMode.setText(getResources().getString(R.string.btn_decay));
                break;
            default:
                break;
        }
    }

    /**
     * ???????????? //GC20190709
     */
    private void switchDensity() {
        if ((mode == TDR) || (mode == SIM)) {
            densityMax = densityMaxTdrSim[rangeState];
        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == DECAY)) {
            densityMax = densityMaxIcmDecay[rangeState];
            //????????????  //GC20191223
            if (range == RANGE_250) {
                densityMax = densityMax / 2;
            }
        }
        density = densityMax;
        tvZoomValue.setText("1 : " + density);
        density = getDensity();

        //?????????????????????
        tvZoomMin.setEnabled(true);
        llHorizontalView.setVisibility(View.VISIBLE);

        tvZoomPlus.setEnabled(true);
        tvZoomMin.setEnabled(true);
        //????????????????????????
        setHorizontalMoveViewOnlyHeight();
    }

    /**
     * ????????????????????????
     */
    private void setHorizontalMoveView() {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(rlWave.getWidth() / density, 30);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mvWave.getParentWidth() * 510 * density / dataLength, getResources().getDimensionPixelSize(R.dimen.dp_20));
        mvWave.setLayoutParams(layoutParams);
    }

    private void setHorizontalMoveViewOnlyHeight() {
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(rlWave.getWidth() / density, 30);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mvWave.getWidth(), getResources().getDimensionPixelSize(R.dimen.dp_20));
        mvWave.setLayoutParams(layoutParams);
    }

    /**
     * ????????????????????????
     */
    private void setHorizontalMoveViewPosition(int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mvWave.getLayoutParams();
        layoutParams.leftMargin = fenzi2 - mvWave.getWidth() / 2;
        mvWave.setLayoutParams(layoutParams);
    }

    private void setMoverPosition(int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mvWave.getLayoutParams();
        layoutParams.leftMargin = position;
        mvWave.setLayoutParams(layoutParams);
    }

    /**
     * ???????????????????????????????????? //GC20190709
     */
    private void initCursor() {
        int zero2 = 0;
        //????????????
        if (mode == SIM) {
            //GC20190712
            zero = simOriginalZero;
            if (range == RANGE_250) {
                zero = zero * 2;
            }
            //GC20200330
            zero2 = simStandardZero;
            if (range == RANGE_250) {
                zero2 = simStandardZero * 2;
            }
        } else {
            zero = 0;
        }
        pointDistance = 255 * densityMax;
        //??????????????????????????????
        calculateDistance(Math.abs(pointDistance - zero));
       /* if(mode == ICM || mode == ICM_DECAY) {
            calculateDistance(Math.abs(pointDistance - zero));
        }
        if(mode == TDR){
            calculateDistance(Math.abs(autoLocation));
        }*/
        //????????????
        positionReal = zero / densityMax;
        positionVirtual = pointDistance / densityMax;

        if (positionReal >= 0) {
            mainWave.setScrubLineReal(positionReal);
        }
        mainWave.setScrubLineVirtual(positionVirtual);
        //GC20200330
        if (mode == SIM) {
            positionSim = zero2 / densityMax;
            mainWave.setScrubLineSim(positionSim);
        } else {
            mainWave.setScrubLineSimDisappear();
        }
    }

    /**
     * @param range ???????????????????????????????????? / ???????????????????????????
     */
    public void setRange(int range) {
        //20200407
        if (allowSetRange == false) {
            return;
        }
        allowSetRange = false;
        this.range = range;

        switch (range) {
            case RANGE_250:
                range = 0x99;
                rangeState = 0;
                //GC20190709
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
                } else if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                }
                gain = 13;
                //?????????????????????   //GC20200313
                tvGainValue.setText("41");
                break;
            case RANGE_500:
                rangeState = 1;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                }
                gain = 13;
                tvGainValue.setText("41");
                break;
            case RANGE_1_KM:
                rangeState = 2;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                }
                gain = 13;
                tvGainValue.setText("41");
                break;
            case RANGE_2_KM:
                rangeState = 3;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("32");
                break;
            case RANGE_4_KM:
                rangeState = 4;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("32");
                break;
            case RANGE_8_KM:
                rangeState = 5;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                }
                gain = 10;
                tvGainValue.setText("32");
                break;
            case RANGE_16_KM:
                rangeState = 6;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("29");
                break;
            case RANGE_32_KM:
                rangeState = 7;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("29");
                break;
            case RANGE_64_KM:
                rangeState = 8;
                switchDensity();
                initCursor();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                }
                gain = 9;
                tvGainValue.setText("29");
                break;
            default:
                break;
        }
        //GC20200428
        selectWaveLength();

        //????????????
        command = COMMAND_RANGE;
        dataTransfer = range;
        startService();
    }

    public void setRangeNoCmd(int range) {
        this.range = range;
        switch (range) {
            case RANGE_250:
                rangeState = 0;
                switchDensity();
                //GC20190709
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m));
                } else if (Constant.CurrentUnit == FT_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_250m_to_ft));
                }
                break;
            case RANGE_500:
                rangeState = 1;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_500m_to_ft));
                }
                break;
            case RANGE_1_KM:
                rangeState = 2;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_1km_to_yingli));
                }
                break;
            case RANGE_2_KM:
                rangeState = 3;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_2km_to_yingli));
                }
                break;
            case RANGE_4_KM:
                rangeState = 4;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_4km_to_yingli));
                }
                break;
            case RANGE_8_KM:
                rangeState = 5;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_8km_to_yingli));
                }
                break;
            case RANGE_16_KM:
                rangeState = 6;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_16km_to_yingli));
                }
                break;
            case RANGE_32_KM:
                rangeState = 7;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_32km_to_yingli));
                }
                break;
            case RANGE_64_KM:
                rangeState = 8;
                switchDensity();
                if (Constant.CurrentUnit == MI_UNIT) {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km));
                } else {
                    tvRangeValue.setText(getResources().getString(R.string.btn_64km_to_yingli));
                }
                break;
            default:
                break;
        }
        //GC20200428
        selectWaveLength();
        command = COMMAND_RANGE;
        //TODO ??????250?????????500?????????????????????250
        if (range == RANGE_250) {
            dataTransfer = 0x11;
        } else {
            dataTransfer = range;
        }
        startService();

    }

    /**
     * @param gain ???????????????????????????????????? / ???????????????????????????
     */
    public void setGain(int gain) {
        this.gain = gain;
        Constant.Gain = gain;
        command = COMMAND_GAIN;
        dataTransfer = gain;
        //???????????????????????????????????????????????????    //GC20200604
        if (gain == 31) {
            tvGainAdd.setEnabled(false);
            tvGainMin.setEnabled(true);
            if (mode == SIM) {
                tvGainAdd.setImageResource(R.drawable.bg_gain_plus_s_false);
                tvGainMin.setImageResource(R.drawable.bg_gain_min_s_selector);
            } else {
                tvGainAdd.setImageResource(R.drawable.bg_gain_plus_false);
                tvGainMin.setImageResource(R.drawable.bg_gain_min_selector);
            }
            gainButtonChanged = true;
        } else if (gain == 0) {
            tvGainAdd.setEnabled(true);
            tvGainMin.setEnabled(false);
            if (mode == SIM) {
                tvGainAdd.setImageResource(R.drawable.bg_gain_plus_s_selector);
                tvGainMin.setImageResource(R.drawable.bg_gain_min_s_false);
            } else {
                tvGainAdd.setImageResource(R.drawable.bg_gain_plus_selector);
                tvGainMin.setImageResource(R.drawable.bg_gain_min_false);
            }
            gainButtonChanged = true;
        } else {
            if (gainButtonChanged) {
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                if (mode == SIM) {
                    tvGainAdd.setImageResource(R.drawable.bg_gain_plus_s_selector);
                    tvGainMin.setImageResource(R.drawable.bg_gain_min_s_selector);
                } else {
                    tvGainAdd.setImageResource(R.drawable.bg_gain_plus_selector);
                    tvGainMin.setImageResource(R.drawable.bg_gain_min_selector);
                }
                gainButtonChanged = false;
            }
        }
        //?????????????????????   //GC20200313
        int temp = s2b(gain);
        tvGainValue.setText(String.valueOf(temp));
        startService();
    }

    /**
     * ?????????????????????31???100    //GC20200313
     * @param s
     * @return
     */
    public int s2b(int s) {
        int b;
        float v = (float) s / 31.0f;
        float v1 = v * 100;
        b = (int) v1;
        return b;
    }

    public void setGainNoCmd(int gain) {
        this.gain = gain;
        command = COMMAND_GAIN;
        dataTransfer = gain;
        tvGainValue.setText(String.valueOf(gain));
    }

    public int getGain() {
        return gain;
    }

    /**
     * @param balance ???????????????????????????????????? / ???????????????????????????
     */
    public void setBalance(int balance) {
        this.balance = balance;
        command = COMMAND_BALANCE;
        dataTransfer = balance;
        //????????????????????????    //GC20200604
        if (balance == 15) {
            tvBalancePlus.setEnabled(false);
            tvBalancePlus.setImageResource(R.drawable.bg_balance_plus_false);
            balanceButtonChanged = true;
        } else if (balance == 0) {
            tvBalanceMin.setEnabled(false);
            tvBalanceMin.setImageResource(R.drawable.bg_balance_min_false);
            balanceButtonChanged = true;
        } else {
            if (balanceButtonChanged) {
                tvBalancePlus.setEnabled(true);
                tvBalancePlus.setImageResource(R.drawable.bg_balance_plus_selector);
                tvBalanceMin.setEnabled(true);
                tvBalanceMin.setImageResource(R.drawable.bg_balance_min_selector);
                balanceButtonChanged = false;
            }
        }
        tvBalanceValue.setText(String.valueOf(balance));
        //????????????
        startService();
    }

    public int getBalance() {
        return balance;
    }

    /**
     * @param delay ???????????????????????????????????? / ???????????????????????????
     */
    public void setDelay(int delay) {
        this.delay = delay;
        tvDelayValue.setText(delay + "??s");
        //????????????????????????    //GC20200613
        if (delay == 0) {
            tvDelayMin.setEnabled(false);
        } else if (delay == 1250) {
            tvDelayPlus.setEnabled(false);
        } else {
            tvDelayPlus.setEnabled(true);
            tvDelayMin.setEnabled(true);
        }
        command = COMMAND_DELAY;
        //GC20200613    ????????????
        dataTransfer = delay / 5;
        //????????????
        startService();
    }

    public int getDelay() {
        return delay;
    }

    /**
     * @param density ??????????????????????????????
     */
    public void setDensity(int density) {
        this.density = density;
        tvZoomValue.setText("1 : " + density);
        organizeClickZoomData();
        displayWave();
    }

    public int getDensity() {
        return density;
    }

    /**
     * @param velocity ??????????????????????????????
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        if (Constant.CurrentUnit == MI_UNIT) {
            tvVopValue.setText(String.valueOf(velocity));
        } else {
            tvVopValue.setText(UnitUtils.miToFt(velocity));
        }
        calculateDistance(Math.abs(pointDistance - zero));

    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocityNoCmd(int velocity) {
        //20200523  ???????????????
        this.velocity = velocity;
        if (Constant.CurrentUnit == MI_UNIT) {
            tvVopValue.setText(String.valueOf(velocity));
        } else {
            tvVopValue.setText(UnitUtils.miToFt(velocity));
        }

    }


    @OnClick({R.id.tv_500m, R.id.tv_250m, R.id.tv_1km, R.id.tv_2km, R.id.tv_4km, R.id.tv_8km, R.id.tv_16km, R.id.tv_32km, R.id.tv_64km,
            R.id.tv_gain_add, R.id.tv_gain_min, R.id.layout_tv_both, R.id.layout_tv_memory, R.id.tv_cursor_plus, R.id.tv_balance_plus, R.id.tv_balance_min, R.id.tv_pulse_width, R.id.tv_cal, R.id.tv_range,
            R.id.tv_file, R.id.tv_home, R.id.tv_zero, R.id.tv_cursor_min, R.id.tv_zoom_plus, R.id.tv_zoom_min, R.id.tv_test, R.id.tv_help,
            R.id.tv_compare,
            R.id.ll_adjust,
            R.id.iv_pulse_width_close, R.id.tv_pulse_width_save,
            R.id.tv_vop_save, R.id.iv_compare_close, R.id.iv_cal_close, R.id.iv_range_close, R.id.iv_records_close,
            R.id.tv_vop_min, R.id.tv_vop_plus,
            R.id.tv_records_save, R.id.tv_file_records,
            R.id.tv_origin, R.id.tv_trigger_delay, R.id.tv_delay_plus, R.id.tv_delay_min, R.id.ll_trigger_delay, R.id.iv_close_trigger_delay,
            R.id.tv_wave_next, R.id.tv_wave_pre})
    public void onClick(View view) {
        //????????????????????????????????????????????????  //GC20200630
        switch (view.getId()) {
            case R.id.tv_file:
                showFileView();
                break;
            case R.id.tv_file_records:
                showRecordsDialog();
                break;
            case R.id.tv_records_save:
                showSaveDialog();
                break;
            case R.id.tv_home:
                finish();
                break;
            case R.id.tv_zero:
                //????????????  //GC20200612
                closeAllView();
                mainWave.setScrubLineReal(positionVirtual);
                positionReal = positionVirtual;
                //???????????????????????????
                zero = pointDistance;
                calculateDistance(0);
                break;
            case R.id.tv_cursor_min:
                closeAllView();
                if (positionVirtual > 0) {

                    int positionVirtualtemp = positionVirtual;
                    positionVirtualtemp -= 1;
                    mainWave.setScrubLineVirtual(positionVirtualtemp);
                    pointDistance = pointDistance + (positionVirtualtemp - positionVirtual) * density;
                    positionVirtual = positionVirtualtemp;
                    Log.e("?????????????????????", "positionVirtual" + positionVirtual);
                    if (positionVirtual == 0) {
                        pointDistance = 0;
                    }
                    calculateDistance(Math.abs(pointDistance - zero));
                    //GT20200619
                    /*int height;
                    if (mode == SIM) {
                        height = Constant.SimData[pointDistance];
                    } else {
                        height = Constant.WaveData[pointDistance];
                    }
                    Log.e("????????????", "???????????????" + height);
                    tvHeight.setText("??????" + height);*/
                }
                break;
            case R.id.tv_cursor_plus:
                closeAllView();
                if (positionVirtual < 509) {
                    int positionVirtualtemp = positionVirtual;
                    positionVirtualtemp += 1;
                    mainWave.setScrubLineVirtual(positionVirtualtemp);
                    pointDistance = pointDistance + (positionVirtualtemp - positionVirtual) * density;
                    positionVirtual = positionVirtualtemp;
                    calculateDistance(Math.abs(pointDistance - zero));
                    //GT20200619
                    /*int height;
                    if (mode == SIM) {
                        height = Constant.SimData[pointDistance];
                    } else {
                        height = Constant.WaveData[pointDistance];
                    }
                    Log.e("????????????", "???????????????" + height);
                    tvHeight.setText("??????" + height);*/
                }
                break;
            case R.id.tv_zoom_plus:
                closeAllView();
                int density = getDensity();
                if (density > 1) {
                    density = density / 2;
                    setDensity(density);
                    tvZoomMin.setEnabled(true);
                }
                //????????????
                if (density == 1) {
                    tvZoomPlus.setEnabled(false);
                }
                break;
            case R.id.tv_zoom_min:
                closeAllView();
                density = getDensity();
                if (density < Constant.DensityMax) {
                    density = density * 2;
                    setDensity(density);
                    tvZoomPlus.setEnabled(true);
                }
                if (density == Constant.DensityMax) {
                    tvZoomMin.setEnabled(false);
                }
                break;
            default:
                break;
        }
        //????????????????????????
        if (!ConnectService.isConnected) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }
        //????????????????????????
        if (Constant.isTesting) {
            return;
        }

        switch (view.getId()) {
            case R.id.ll_adjust:
                break;
            case R.id.tv_wave_pre:
                //SIM???8?????????1-8
                selectSim = getSelectSim();
                if (selectSim > 1) {
                    selectSim--;
                    setSelectSim(selectSim);
                }
                break;
            case R.id.tv_wave_next:
                selectSim = getSelectSim();
                if (selectSim < 8) {
                    selectSim++;
                    setSelectSim(selectSim);
                }
                break;
            case R.id.iv_close_trigger_delay:
                closeTriggerDelayView();
                break;
            case R.id.tv_origin:
                //??????????????????    //GC20200612
                int simZero = zero;
                if (mode == SIM && range == RANGE_250) {
                    simZero = simZero / 2;
                }
                setSimZero(simZero);
                break;
            case R.id.tv_trigger_delay:
                showTriggerDelayView();
                break;
            case R.id.tv_delay_plus:
                int delay = getDelay();
                //jk20201130 ??????????????????????????????
                if(mode == SIM){
                    if (delay < 1250) {
                        delay = delay + 15;
                        if(delay >1250){
                            delay = 1250;
                        }
                        //GC20190704 ????????????????????????   (?????????0???1250?????????????????????5??????250???)
                        setDelay(delay);
                    }
                }
                else if (delay < 1250) {
                    delay = delay + 5;
                    //GC20190704 ????????????????????????   (?????????0???1250?????????????????????5??????250???)
                    setDelay(delay);
                }
                break;
            case R.id.tv_delay_min:
                delay = getDelay();
                if(mode == SIM){  //jk20201130 ??????????????????????????????
                    if (delay > 0) {
                        delay = delay - 15;
                        if(delay < 0){
                            delay = 0;
                        }
                        //GC20190704 ????????????????????????   (?????????0???1250?????????????????????5??????250???)
                        setDelay(delay);
                    }
                }
                else if (delay > 0) {
                    delay = delay - 5;
                    setDelay(delay);
                }
                break;
            case R.id.iv_compare_close:
                closeCompareView();
                break;
            case R.id.iv_cal_close:
                closeCalView();
                break;
            case R.id.iv_pulse_width_close:
                closePulseWidthView();
                break;
            case R.id.iv_range_close:
                closeRangeView();
                break;
            case R.id.iv_records_close:
                closeFileView();
                break;
            case R.id.tv_vop_save:
                saveVop();
                llCal.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_pulse_width_save:
                savePulseWidth();
                break;
            case R.id.tv_250m:
                setRange(0x99);
                setGain(gain);
                //?????????????????????????????????????????????????????????TDR???????????????????????????    //GC20200331
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 40;
                        setPulseWidth(40);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(40));
                }
                //?????????????????????SIM???????????????   //GC20200527
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 320;
                        setPulseWidth(320);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_500m:
                setRange(0x11);
                setGain(gain);
                //GC20200331
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 40;
                        setPulseWidth(40);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(40));
                }
                //GC20200527
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 320;
                        setPulseWidth(320);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_1km:
                setRange(0x22);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 80;
                        setPulseWidth(80);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(80));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 320;
                        setPulseWidth(320);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_2km:
                setRange(0x33);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 160;
                        setPulseWidth(160);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(160));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 720;
                        setPulseWidth(720);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_4km:
                setRange(0x44);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 320;
                        setPulseWidth(320);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(320));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidth = 2560;
                        setPulseWidth(2560);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_8km:
                setRange(0x55);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 640;
                        setPulseWidth(640);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(640));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidth = 3600;
                        setPulseWidth(3600);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_16km:
                setRange(0x66);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 1280;
                        setPulseWidth(1280);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(1280));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 7120;
                        setPulseWidth(7120);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_32km:
                setRange(0x77);
                setGain(gain);
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 2560;
                        setPulseWidth(2560);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(2560));
                }
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 10200;
                        setPulseWidth(10200);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_64km:
                setRange(0x88);
                setGain(gain);
                //GC20200331
                if (!hasSavedPulseWidth && mode == TDR) {
                    handler.postDelayed(() -> {
                        pulseWidth = 5120;
                        setPulseWidth(5120);
                    }, 20);
                    etPulseWidth.setText(String.valueOf(5120));
                }
                //GC20200527
                if (mode == SIM) {
                    handler.postDelayed(() -> {
                        pulseWidthSim = 10200;
                        setPulseWidth(10200);
                    }, 20);
                }
                handler.postDelayed(this::clickTest, 50);
                tvGainAdd.setEnabled(true);
                tvGainMin.setEnabled(true);
                break;
            case R.id.tv_gain_add:
                int gain = getGain();
                if (gain < 31) {
                    gain++;
                    //GC20190704 ????????????????????????   (????????????0-31???)
                    setGain(gain);
                }
                closeAllView();
                break;
            case R.id.tv_gain_min:
                gain = getGain();
                if (gain > 0) {
                    gain--;
                    //GC20190704 ????????????????????????   (????????????0-31???)
                    setGain(gain);
                }
                closeAllView();
                break;
            case R.id.tv_balance_plus:
                int balance = getBalance();
                if (balance < 15) {
                    balance++;
                    setBalance(balance);
                }
                closeAllView();
                break;
            case R.id.tv_balance_min:
                balance = getBalance();
                if (balance > 0) {
                    balance--;
                    setBalance(balance);
                }
                closeAllView();
                break;
            case R.id.tv_vop_min:
                velocity = getVelocity();
                if (velocity > 90) {
                    velocity--;
                    setVelocity(velocity);
                    saveVop();
                }
                break;
            case R.id.tv_vop_plus:
                int velocity = (int) getVelocity();
                if (velocity < 300) {
                    velocity++;
                    setVelocity(velocity);
                    saveVop();
                }
                break;
            case R.id.tv_pulse_width:
                showPulseWidth();
                break;
            case R.id.layout_tv_memory:
                clickMemory();
                break;
            case R.id.layout_tv_both:
                clickCompare();
                break;
            case R.id.tv_compare:
                showCompareView();
                break;
            case R.id.tv_cal:
                showCalView();
                break;
            case R.id.tv_range:
                showRangeView();
                break;
            case R.id.tv_test:
                isReceiveData = true;
                clickTest();
                step = 8;   //jk20200716
                count = 6;
                isLongClick = false;  //jk20200716
                break;
            case R.id.tv_help:
                closeAllView();
                showHelpModeDialog();
                break;
            default:
                break;
        }
    }

    /**
     * ????????????????????????     //jk20200715
     */
    @OnLongClick ({R.id.tv_test})
    public boolean onLongClick(View view){
        isLongClick = true;
        isReceiveData = true;
        clickTest();
        return true;

    }

    /**
     * ????????????????????????   //GC20200327
     */
    private void showHelpModeDialog() {
        HelpModeDialog helpModeDialog = new HelpModeDialog(this);
        Constant.ModeValue = mode;
        if (!helpModeDialog.isShowing()) {
            helpModeDialog.show();
        }
    }

    public int getSelectSim() {
        return selectSim;
    }

    /**
     * @param selectSim SIM?????????????????????
     */
    public void setSelectSim(int selectSim) {
        Log.e("SIM??????", "setSelectSim");
        tvWaveText.setVisibility(View.VISIBLE);
        this.selectSim = selectSim;
        //????????????????????????????????????    //GC20200604
        if (selectSim == 1) {
            tvWavePre.setEnabled(false);
            tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_false);
            tvWaveNext.setEnabled(true);
            tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_selector);
            waveButtonChanged = true;
        } else if (selectSim == 8) {
            tvWavePre.setEnabled(true);
            tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_selector);
            tvWaveNext.setEnabled(false);
            tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_false);
            waveButtonChanged = true;
        } else {
            if (waveButtonChanged) {
                tvWavePre.setEnabled(true);
                tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_selector);
                tvWaveNext.setEnabled(true);
                tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_selector);
                waveButtonChanged = false;
            }
        }
        switch (selectSim) {
            case 1:
                System.arraycopy(simDraw1, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData1;
                tvWaveValue.setText(R.string.wave_one);
                break;
            case 2:
                System.arraycopy(simDraw2, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData2;
                tvWaveValue.setText(R.string.wave_two);
                break;
            case 3:
                System.arraycopy(simDraw3, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData3;
                tvWaveValue.setText(R.string.wave_three);
                break;
            case 4:
                System.arraycopy(simDraw4, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData4;
                tvWaveValue.setText(R.string.wave_four);
                break;
            case 5:
                System.arraycopy(simDraw5, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData5;
                tvWaveValue.setText(R.string.wave_five);
                break;
            case 6:
                System.arraycopy(simDraw6, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData6;
                tvWaveValue.setText(R.string.wave_six);
                break;
            case 7:
                System.arraycopy(simDraw7, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData7;
                tvWaveValue.setText(R.string.wave_seven);
                break;
            case 8:
                System.arraycopy(simDraw8, 0, waveCompare, 0, 510);
                Constant.SimData = Constant.TempData8;
                tvWaveValue.setText(R.string.wave_eight);
                break;
            default:
                break;
        }
        displayWave();
    }

    /**
     * @param simZero SIM???????????????????????????    //GC20190712
     */
    public void setSimZero(int simZero) {
        this.simOriginalZero = simZero;
        //SIM?????????????????????????????????   //GC20200612
        this.simStandardZero = simZero;
        positionSim = simStandardZero / density;
        mainWave.setScrubLineSim(positionSim);
        StateUtils.setInt(ModeActivity.this, AppConfig.CURRENT_CURSOR_POSITION, simZero);
        Toast.makeText(this, getResources().getString(R.string.cursor_zero_set_success), Toast.LENGTH_SHORT).show();

    }

    private void saveVop() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PARAM_INFO_KEY);
        //????????????????????????  //20200522
        if (paramInfo != null) {
            paramInfo.setCableVop(String.valueOf(velocity));
        } else {
            paramInfo = new ParamInfo();
            paramInfo.setCableVop(String.valueOf(velocity));
        }
        Constant.Velocity = velocity;
        StateUtils.setObject(ModeActivity.this, paramInfo, Constant.PARAM_INFO_KEY);

    }

    /**
     * ??????????????????????????????????????????????????????View???????????????????????????????????????????????????????????????View??????
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private boolean touchEventInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        if (y >= top && y <= bottom && x >= left && x <= right) {
            return true;
        }

        return false;
    }


    private void showFileView() {
        llRecords.setVisibility(View.VISIBLE);
        llRecords.setOnClickListener(null);
        closeCompareView();
        closeRangeView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeFileView() {
        llAdjust.setVisibility(View.VISIBLE);
        llRecords.setVisibility(View.GONE);
    }

    private void showRangeView() {
        //?????????????????????????????????????????????????????????????????????????????????????????????
        if (!ConnectService.isConnected) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }
        llRange.setVisibility(View.VISIBLE);
        llRange.setOnClickListener(null);
        closeFileView();
        closeCompareView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeRangeView() {
        llAdjust.setVisibility(View.VISIBLE);
        llRange.setVisibility(View.GONE);
    }

    private void showTriggerDelayView() {
        llTriggerDelay.setVisibility(View.VISIBLE);
        closeFileView();
        closeRangeView();
        closeCalView();
        closeCompareView();
        closePulseWidthView();
    }

    private void closeTriggerDelayView() {
        llAdjust.setVisibility(View.VISIBLE);
        llTriggerDelay.setVisibility(View.GONE);
    }

    private void showCalView() {
        llCal.setVisibility(View.VISIBLE);
        llCal.setOnClickListener(null);
        closeFileView();
        closeRangeView();
        closeCompareView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeCalView() {
        llAdjust.setVisibility(View.VISIBLE);
        llCal.setVisibility(View.GONE);
    }


    private void closePulseWidthView() {
        llAdjust.setVisibility(View.VISIBLE);
        llPulseWidth.setVisibility(View.GONE);
    }


    private void closeCompareView() {
        llAdjust.setVisibility(View.VISIBLE);
        llCompare.setVisibility(View.GONE);
    }

    private void showCompareView() {
        llCompare.setVisibility(View.VISIBLE);
        llCompare.setOnClickListener(null);
        closeFileView();
        closeRangeView();
        closeCalView();
        closeTriggerDelayView();
        closePulseWidthView();
    }

    private void closeAllView() {
        closeCalView();
        closeCompareView();
        closeRangeView();
        closeTriggerDelayView();
        closeFileView();
        closePulseWidthView();
    }

    /**
     * ????????????????????????????????????
     */
    private void showPulseWidth() {
        llPulseWidth.setOnClickListener(null);
        llPulseWidth.setVisibility(View.VISIBLE);
        closeFileView();
        closeRangeView();
        closeCompareView();
        closeCalView();
        closeTriggerDelayView();
    }

    /**
     * ????????????????????????
     */
    private void savePulseWidth() {
        // 01 ??????????????????
        if (etPulseWidth.getText().toString().isEmpty() || "0".equals(etPulseWidth.getText().toString()) ) {
            //???????????????????????????????????????   //GC20200331
            etPulseWidth.setText(String.valueOf(pulseWidth));
            hasSavedPulseWidth = false;
        } else {
            pulseWidth = Integer.valueOf(etPulseWidth.getText().toString());
            //?????????????????? //GC20200331
            hasSavedPulseWidth = true;
        }
        // 02 ???????????????????????????
        savePulseWidthInfo();
        // 03 ????????????
        setPulseWidth(pulseWidth);
        closePulseWidthView();
    }

    /**
     * ??????????????????
     */
    private void savePulseWidthInfo() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(ModeActivity.this, Constant.PULSE_WIDTH_INFO_KEY);
        if (paramInfo != null) {
            paramInfo.setPulseWidth(pulseWidth);
        } else {
            paramInfo = new ParamInfo();
            paramInfo.setPulseWidth(pulseWidth);
        }
        StateUtils.setObject(ModeActivity.this, paramInfo, Constant.PULSE_WIDTH_INFO_KEY);

    }

    /**
     * ?????????????????????
     */
    private void setPulseWidth(int pulseWidth) {
        command = COMMAND_PULSE_WIDTH;
        dataTransfer = calPulseWidth(pulseWidth);
        startService();
    }

    /**
     * ???????????????????????????????????????255-X/40;X????????????
     */
    private int calPulseWidth(int pulseWidth) {

        if (pulseWidth < 0 || pulseWidth > 7000) {
            return 0;
        }
        pulseWidth = 255 - pulseWidth / 40;
        return pulseWidth;
    }

    /**
     * ???????????????????????????
     */
    private void showSaveDialog() {
        closeFileView();
        SaveRecordsDialog saveRecordsDialog = new SaveRecordsDialog(this);
        Constant.ModeValue = mode;
        //TODO 20191226 ??????zero???pointDistance
        saveRecordsDialog.setPositionReal(zero);
        saveRecordsDialog.setPositionVirtual(pointDistance);
        if (!saveRecordsDialog.isShowing()) {
            saveRecordsDialog.show();
        }
    }

    /**
     * ???????????????????????????
     */
    private void showRecordsDialog() {
        closeFileView();
        ShowRecordsDialog showRecordsDialog = new ShowRecordsDialog(this);
        showRecordsDialog.setMode(mode);
        if (!showRecordsDialog.isShowing()) {
            showRecordsDialog.show();
        }

    }

    /**
     * ????????????
     */
    private void clickTest() {
        //TODO 20200407 ?????????????????????????????????????????????????????????????????????????????????????????????
        if (!ConnectService.isConnected) {
            Toast.makeText(ModeActivity.this, R.string.test_on_no_connect, Toast.LENGTH_SHORT).show();
            return;
        }
        //TODO 20200415 ??????????????????????????????
        if (Constant.isTesting) {
            return;
        }
        //TODO 20200407 ?????????????????????????????????????????????????????????????????????????????????
        Constant.isTesting = true;
        tvTest.setEnabled(false);

        Constant.SaveToDBGain = Constant.Gain;
        closeAllView();
        llRange.setVisibility(View.INVISIBLE);

        if (tDialog != null) {
            tDialog.dismiss();
        }
        //???????????????
        if (mode == TDR) {
            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.receiving_data)
                    .setScreenWidthAspect(this, 0.25f)
                    .setCancelableOutside(false)
                    .create()
                    .show();
            Log.e("DIA", " ????????????????????????" + " TDR");
            command = COMMAND_TEST;
            dataTransfer = TESTING;
            startService();
            handler.postDelayed(() -> {
                command = COMMAND_RECEIVE_WAVE;
                dataTransfer = 0x11;
                startService();
                //Log.e("??????????????????", "??????????????????????????????");
                //??????????????????????????? //20200523
                alreadyDisplayWave = false;
            }, 20);

        } else if ((mode == ICM) || (mode == ICM_DECAY) || (mode == SIM) || (mode == DECAY)) {
            tDialog = new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.wait_trigger)
                    .setScreenWidthAspect(this, 0.3f)
                    .setCancelableOutside(false)
                    .addOnClickListener(R.id.tv_cancel)
                    .setOnKeyListener(new DialogInterface.OnKeyListener() {
                      //  @OverriderangeSave //jk20220831 ????????????
                      @Override
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

                            if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                                Toast.makeText(ModeActivity.this, R.string.ask_cancel, Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    })
                    .setOnViewClickListener(new OnViewClickListener() {
                        @Override
                        public void onViewClick(BindViewHolder viewHolder, View view,
                                                TDialog tDialog) {
                            //????????????????????????    //20200523    //GC
                            if (canClickCancelButton) {
                                //????????????????????????????????????
                                canClickCancelButton = false;
                                if (!alreadyDisplayWave) {
                                    tvZoomMin.setEnabled(false);
                                    tvZoomPlus.setEnabled(false);
                                    tvWavePre.setEnabled(false);
                                    tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_false);
                                    tvWaveNext.setEnabled(false);
                                    tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_false);
                                    //??????????????????  //GC20200604
                                } else {
                                    tvZoomMin.setEnabled(true);
                                    tvZoomPlus.setEnabled(true);
                                    tvWavePre.setEnabled(true);
                                    tvWavePre.setImageResource(R.drawable.bg_wave_pre_s_selector);
                                    tvWaveNext.setEnabled(true);
                                    tvWaveNext.setImageResource(R.drawable.bg_wave_next_s_selector);
                                }
                                tDialog.dismiss();
                                //TODO 20200407 ?????????????????????????????????????????????
                                tvTest.setEnabled(true);
                                Constant.isTesting = false;
                                allowSetRange = true;

                                command = COMMAND_TEST;
                                dataTransfer = CANCEL_TEST;
                                startService();
                            }
                        }
                    })
                    .create()
                    .show();
            //TODO 20200507 ??????????????????????????????
            canClickCancelButton = false;
            handler.postDelayed(() -> {
                canClickCancelButton = true;
            }, 300);
            Log.e("DIA", " ??????????????????");
            command = COMMAND_TEST;
            dataTransfer = TESTING;
            startService();

            //EN20200324
            ConnectService.canAskPower = false;
    }
    }

    /**
     * ???????????????????????????  //GC20190703
     */
    public void clickCompare() {
        closeCompareView();
        if (isMemory) {
            //?????????   //GC20190703
            if ((modeBefore == mode) && (rangeBefore == range)) {
                isCom = !isCom;
                myChartAdapterMainWave.setmTempArray(waveDraw);
                myChartAdapterMainWave.setShowCompareLine(isCom);
                //myChartAdapterMainWave.setmCompareArray(waveCompare);
                organizeCompareWaveData(currentStart);
                myChartAdapterMainWave.setmCompareArray(waveCompareDraw);
                myChartAdapterMainWave.notifyDataSetChanged();
            } else {
                Toast.makeText(this, getResources().getString(R.string.You_can_not_compare), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.You_have_no_memory_data_can_not_compare), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ???????????????????????????  //GC20190703
     */
    public void clickMemory() {
        if (alreadyDisplayWave) {
            closeCompareView();
            isMemory = true;
            waveCompare = new int[Constant.WaveData.length];
            System.arraycopy(Constant.WaveData, 0, waveCompare, 0, Constant.WaveData.length);
            //?????????????????????????????????   //?????????   //GC20190703
            modeBefore = mode;
            rangeBefore = range;
        }

    }

    /**
     *?????????????????????
     */
    public void startService() {
        Intent intent = new Intent(ModeActivity.this, ConnectService.class);
        //????????????
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_MODE_KEY, mode);
        bundle.putInt(BUNDLE_COMMAND_KEY, command);
        bundle.putInt(BUNDLE_DATA_TRANSFER_KEY, dataTransfer);
        intent.putExtra(BUNDLE_PARAM_KEY, bundle);
        startService(intent);
    }

    public boolean getReceiveData() {
        return isReceiveData;
    }

    public void setReceiveData(boolean receiveData) {
        isReceiveData = receiveData;
    }

    protected void hideBottomUIMenu() {
        //?????????????????????????????????
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * //G? ???????????????
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) { //No call for super(). Bug on API Level > 11. // super.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

}
