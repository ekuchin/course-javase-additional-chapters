<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <tr>
                        <td> Имя кота </td>
                        <td> Порода </td>
                        <td> Вес </td>
                    </tr>
                    <xsl:for-each select="cats/cat">
                        <xsl:sort select="breed" order="descending"/>
                            <tr>
                                <td> <xsl:value-of select="name"/> </td>
                                <td> <xsl:value-of select="breed"/> </td>
                                <td> <xsl:value-of select="weight"/> </td>
                            </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>