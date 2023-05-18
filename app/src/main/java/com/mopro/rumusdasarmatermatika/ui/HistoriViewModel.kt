package com.mopro.rumusdasarmatermatika.ui

import androidx.lifecycle.ViewModel
import com.mopro.rumusdasarmatermatika.db.HasilDao

class HistoriViewModel(db: HasilDao) : ViewModel(){
    val data = db.getLastHasil()
}