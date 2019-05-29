using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Transmission : MonoBehaviour
{
    double radius;
    Transform target;
    Vector3 difference;

    public void Start()
    {
        target = GetComponent<Rigidbody2D>().transform;
    }

    void Update()
    {
        // Rotate the camera every frame so it keeps looking at the target
        difference = target.parent.transform.position - target.position;
        float angle = Mathf.Atan2(difference.y, difference.x);
        transform.localPosition = new Vector2((float)(radius * Mathf.Cos(angle)), (float)(radius * Mathf.Sin(angle)));
            }
}
