package com.app.moviedb.features.detail.models

import android.content.Context
import com.airbnb.epoxy.*

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CommentEpoxyRecyclerView(context: Context) : EpoxyRecyclerView(context = context) {
    @ModelProp(ModelProp.Option.DoNotHash)
    fun setEpoxyController(controller: EpoxyController) {
        setControllerAndBuildModels(controller)
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
    }

    @ModelProp
    override fun setModels(models: List<EpoxyModel<*>>) {
        // remove super method because we use PagedController for models build.
    }
}