package me.tatocaster.covidstats.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.tatocaster.core.base.*
import me.tatocaster.core.mixins.ProgressBarMixin
import me.tatocaster.covidstats.R
import me.tatocaster.covidstats.inject
import timber.log.Timber
import javax.inject.Inject

class CovidStatsFragment : BaseFragment(), ProgressBarMixin {
    lateinit var covidStatsViewModel: CovidStatsViewModel

    @Inject
    lateinit var viewModelFactory: CovidStatsViewModelFactory

    @Inject
    lateinit var activityContext: Context

    private lateinit var tvTotalCasesCount: TextView
    private lateinit var tvTotalRecoveredCount: TextView
    private lateinit var tvTotalDeathCount: TextView
    private lateinit var bRefreshStats: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        return inflater.inflate(R.layout.fragment_covid_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViewsById(view)

        covidStatsViewModel = ViewModelProvider(this, viewModelFactory)[CovidStatsViewModel::class.java]

        covidStatsViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            applyProgressbarMixin(state, view.findViewById(R.id.progressBar))
            when (state) {
                is JustInitial -> {
                }
                is JustLoading -> {
                }
                is JustError -> {
                    bRefreshStats.isEnabled = true
                    Toast.makeText(activityContext, R.string.server_error, Toast.LENGTH_SHORT)
                        .show()
                }
                is JustSuccess -> {
                }
                is CovidStatsViewModel.CasesLoaded -> {
                    bRefreshStats.isEnabled = true
                    tvTotalCasesCount.text = state.payload.totalConfirmed.toString()
                    tvTotalRecoveredCount.text = state.payload.totalRecovered.toString()
                    tvTotalDeathCount.text = state.payload.totalDeaths.toString()
                }
                is JustUnknownError -> {
                    Toast.makeText(activityContext, R.string.server_error, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> throw UndefinedStateException(state)
            }
        })
        bRefreshStats.setOnClickListener {
            bRefreshStats.isEnabled = false
            Toast.makeText(activityContext, R.string.loading, Toast.LENGTH_SHORT).show()
            covidStatsViewModel.getCovidCases()
        }
    }

    private fun findViewsById(view: View) {
        tvTotalCasesCount = view.findViewById(R.id.tvTotalCasesCount)
        tvTotalRecoveredCount = view.findViewById(R.id.tvTotalRecoveredCount)
        tvTotalDeathCount = view.findViewById(R.id.tvTotalDeathCount)
        bRefreshStats = view.findViewById(R.id.bRefreshStats)
    }

    companion object {
        fun newInstance() = CovidStatsFragment()
    }
}