import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.R

class DialogLogin : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_profesor, null))
                // Add action buttons
                .setPositiveButton("aceptar",
                    DialogInterface.OnClickListener { _, _ ->
                        // sign in the user ...
                    })
                .setNegativeButton("cancelar",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })
                .setNeutralButton("Erregistratu",
                DialogInterface.OnClickListener{_,_ ->
                    dialog?.cancel()
                    DialogRegistro().show(parentFragmentManager, "RegisterDialog")
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class DialogRegistro : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_registro, null))
                // Add action buttons
                .setPositiveButton("Registrarse",
                    DialogInterface.OnClickListener { _, _ ->
                        // sign in the user ...
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}