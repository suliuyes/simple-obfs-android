package com.github.shadowsocks.plugin.obfs_local

import java.io.{File, FileNotFoundException}

import android.net.Uri
import android.os.ParcelFileDescriptor
import com.github.shadowsocks.plugin.{NativePluginProvider, PathProvider}

/**
  * @author Mygod
  */
final class BinaryProvider extends NativePluginProvider {
  override protected def populateFiles(provider: PathProvider): Unit = provider.addPath("obfs_local")

  override def openFile(uri: Uri): ParcelFileDescriptor = uri.getPath match {
    case "/obfs_local" => ParcelFileDescriptor.open(
      new File(getContext.getApplicationInfo.nativeLibraryDir + "/libobfs-local.so"),
      ParcelFileDescriptor.MODE_READ_ONLY)
    case _ => throw new FileNotFoundException()
  }
}
