package kz.almat.myapplication

class Controller(var data: Data, var view: MainActivity) {
    fun getAllData(): HashMap<String, Int> {
        return data.arr
    }
}