DESCRIPTION = "gstreamer dvbmediasink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base libdca"

GSTVERSION = "1.0"

SRC_URI = " \
	git://git.code.sf.net/p/openpli/gst-plugin-dvbmediasink;protocol=git;branch=gst-1.0 \
	file://gstreamer10_dvbmediasink_vuplus.patch \
	file://dvbmediasink_h265.patch \
	file://audiosink_change_message_mode_to_sync.diff \
"
#SRCREV = "${AUTOREV}"
SRCREV = "1e99787f3387d3dd1e6167af73597674bf8fe37d"

S = "${WORKDIR}/git"

inherit gitpkgv

PV = "${GSTVERSION}+git${SRCPV}"
PKGV = "${GSTVERSION}+git${GITPKGV}"
PR = "r3"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-${GSTVERSION}/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-${GSTVERSION}/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-${GSTVERSION}/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-${GSTVERSION}/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OECONF = "${DVBMEDIASINK_CONFIG} --with-gstversion=${GSTVERSION}"

RPROVIDES_${PN} = "gst-plugin-dvbmediasink"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-eac3"
